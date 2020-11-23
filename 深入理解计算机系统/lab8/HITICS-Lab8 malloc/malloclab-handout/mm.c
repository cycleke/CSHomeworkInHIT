#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "memlib.h"
#include "mm.h"

team_t team = {
    /* Team name */
    "HIT",
    /* First member's full name */
    "cycleke",
    /* First member's email address */
    "cycleke@gmail.com",
    /* Second member's full name (leave blank if none) */
    "",
    /* Second member's email address (leave blank if none) */
    ""};

#define POWER_LIMIT 32

/* btyes */
#define WSIZE 4
#define DSIZE 8

/* words */
#define CHUNK ((1 << 12) / WSIZE)
#define HDR_SIZE 1
#define FTR_SIZE 1
#define HDR_FTR_SIZE 2
#define PRED_FIELD_SIZE 1
#define EPILOG_SIZE 2

#define GET(p) (*(unsigned int *)(p))
#define PUT(p, val) (*(unsigned int *)(p) = (unsigned int)(val))

/* 内存对齐 */
#define ALIGN(size) (((size) + 0x7) & ~0x7)
#define EVENROUND(x) ((x + 0x1) & ~0x1)

#define PACK(size, alloc) (((size) << 3) | (alloc))
#define GET_SIZE(p) (GET(p) >> 3)
#define GET_ALLOC(p) (GET(p) & 0x1)
#define GET_TOTAL_SIZE(head) (GET_SIZE(head) + HDR_FTR_SIZE)

#define HDRP(head) ((char **)(head))
#define FTRP(head) ((char **)(head) + GET_SIZE(head) + HDR_SIZE)

#define GET_LIST_PTR(i) (*(lists + i))
#define SET_LIST_PTR(i, ptr) (*(lists + i) = (char *)(ptr))

#define SET_PTR(p, ptr) (*(char **)(p) = (char *)(ptr))

#define GET_PTR_PRED_FIELD(ptr) ((char **)(ptr) + HDR_SIZE)
#define GET_PTR_SUCC_FIELD(ptr) ((char **)(ptr) + HDR_SIZE + PRED_FIELD_SIZE)

#define GET_PRED(bp) (*GET_PTR_PRED_FIELD(bp))
#define GET_SUCC(bp) (*GET_PTR_SUCC_FIELD(bp))

#define PREV_BLOCK_IN_HEAP(head)                                               \
  ((char **)(head) - (GET_TOTAL_SIZE((char **)(head) - (FTR_SIZE))))
#define NEXT_BLOCK_IN_HEAP(head) (FTRP(head) + FTR_SIZE)

static char **lists, **heap_ptr;

static int pow2roundup(int x) {
  /* Copy from
   * https://stackoverflow.com/questions/364985/algorithm-for-finding-the-smallest-power-of-two-thats-greater-or-equal-to-a-giv
   */
  if (x < 0) return 0;
  --x;
  x |= x >> 1;
  x |= x >> 2;
  x |= x >> 4;
  x |= x >> 8;
  x |= x >> 16;
  return x + 1;
}

static int find_list_index(size_t words) {
  int index = 0;
  while (index < POWER_LIMIT && words > 1) {
    ++index;
    words >>= 1;
  }
  return index;
}

static void add_block_into_list(char **bp) {
  size_t size = GET_SIZE(bp);
  int index = find_list_index(size);

  char **front = (char **)GET_LIST_PTR(index), **back = NULL;

  if (size == 0) return;

  if (front == NULL) {
    SET_LIST_PTR(index, bp);
    SET_PTR(GET_PTR_PRED_FIELD(bp), NULL);
    SET_PTR(GET_PTR_SUCC_FIELD(bp), NULL);
    return;
  }

  if (size >= GET_SIZE(front)) {
    SET_LIST_PTR(index, bp);
    SET_PTR(GET_PTR_PRED_FIELD(front), bp);
    SET_PTR(GET_PTR_SUCC_FIELD(bp), front);
    SET_PTR(GET_PTR_PRED_FIELD(bp), NULL);
    return;
  }

  while (front != NULL && GET_SIZE(front) > size) {
    back = front;
    front = (char **)GET_SUCC(front);
  }

  if (front == NULL) {
    SET_PTR(GET_PTR_SUCC_FIELD(back), bp);
    SET_PTR(GET_PTR_PRED_FIELD(bp), back);
    SET_PTR(GET_PTR_SUCC_FIELD(bp), NULL);
  } else {
    SET_PTR(GET_PTR_SUCC_FIELD(back), bp);
    SET_PTR(GET_PTR_PRED_FIELD(bp), back);
    SET_PTR(GET_PTR_SUCC_FIELD(bp), front);
    SET_PTR(GET_PTR_PRED_FIELD(front), bp);
  }
}

static void remove_block_from_list(char **bp) {
  assert(bp);
  char **prev_block = (char **)GET_PRED(bp);
  char **next_block = (char **)GET_SUCC(bp);
  size_t size = GET_SIZE(bp);
  int index;

  if (size == 0) return;

  if (prev_block == NULL) {
    index = find_list_index(size);
    SET_LIST_PTR(index, next_block);
  } else {
    SET_PTR(GET_PTR_SUCC_FIELD(prev_block), next_block);
  }

  if (next_block != NULL) SET_PTR(GET_PTR_PRED_FIELD(next_block), prev_block);

  SET_PTR(GET_PTR_PRED_FIELD(bp), NULL);
  SET_PTR(GET_PTR_SUCC_FIELD(bp), NULL);
}

static void *coalesce(void *bp) {
  char **prev_block = PREV_BLOCK_IN_HEAP(bp);
  char **next_block = NEXT_BLOCK_IN_HEAP(bp);
  unsigned prev_status = GET_ALLOC(prev_block);
  unsigned next_status = GET_ALLOC(next_block);
  size_t size = GET_SIZE(bp);

  if (prev_status && next_status) {
  } else if (prev_status && !next_status) {
    remove_block_from_list(next_block);
    size += GET_TOTAL_SIZE(next_block);
    PUT(bp, PACK(size, 0));
    PUT(FTRP(next_block), PACK(size, 0));
  } else if (!prev_status && next_status) {
    remove_block_from_list(prev_block);
    size += GET_TOTAL_SIZE(prev_block);
    PUT(prev_block, PACK(size, 0));
    PUT(FTRP(bp), PACK(size, 0));
    bp = prev_block;
  } else {
    remove_block_from_list(prev_block);
    remove_block_from_list(next_block);
    size += GET_TOTAL_SIZE(prev_block) + GET_TOTAL_SIZE(next_block);
    PUT(prev_block, PACK(size, 0));
    PUT(FTRP(next_block), PACK(size, 0));
    bp = prev_block;
  }
  return bp;
}

static void *extend_heap(size_t words) {
  char **bp, **end_pointer;
  size_t words_extend = EVENROUND(words);
  size_t words_tot_extend = words_extend + HDR_FTR_SIZE;

  if ((int)(bp = mem_sbrk(words_tot_extend * WSIZE)) == -1) return NULL;

  bp -= EPILOG_SIZE;

  PUT(bp, PACK(words_extend, 0));
  PUT(FTRP(bp), PACK(words_extend, 0));

  end_pointer = bp + words_tot_extend;
  PUT(end_pointer, PACK(0, 1));
  PUT(FTRP(end_pointer), PACK(0, 1));
  return bp;
}

static void *find_free_block(size_t words) {
  char **bp;
  int index = find_list_index(words);

  if ((bp = (char **)GET_LIST_PTR(index)) != NULL && GET_SIZE(bp) >= words) {
    for (;;) {
      if (GET_SIZE(bp) == words) return bp;
      if (GET_SUCC(bp) == NULL || GET_SIZE(GET_SUCC(bp)) < words) return bp;
      bp = (char **)GET_SUCC(bp);
    }
  }
  ++index;
  while (GET_LIST_PTR(index) == NULL && index + 1 < POWER_LIMIT) ++index;
  if ((bp = (char **)GET_LIST_PTR(index)) != NULL)
    while (GET_SUCC(bp) != NULL) bp = (char **)GET_SUCC(bp);
  return bp;
}

static void alloc_free_block(void *bp, size_t words) {
  size_t bp_size = GET_SIZE(bp);
  size_t bp_tot_size = bp_size + HDR_FTR_SIZE;

  size_t needed_size = words;
  size_t needed_tot_size = words + HDR_FTR_SIZE;

  int new_block_tot_size = bp_tot_size - needed_tot_size;
  int new_block_size = new_block_tot_size - HDR_FTR_SIZE;

  char **new_block;

  if (new_block_tot_size > 0) {
    new_block = (char **)bp + needed_tot_size;
    PUT(new_block, PACK(new_block_size, 0));
    PUT(FTRP(new_block), PACK(new_block_size, 0));

    PUT(bp, PACK(needed_size, 1));
    PUT(FTRP(bp), PACK(needed_size, 1));

    new_block = coalesce(new_block);
    add_block_into_list(new_block);
  } else {
    if (new_block_size == 0) needed_size += HDR_FTR_SIZE;
    PUT(bp, PACK(needed_size, 1));
    PUT(FTRP(bp), PACK(needed_size, 1));
  }
}

int mm_init() {
  if ((int)(lists = mem_sbrk(POWER_LIMIT * sizeof(char *))) == -1) return -1;

  for (int i = 0; i < POWER_LIMIT; ++i) SET_LIST_PTR(i, NULL);

  mem_sbrk(WSIZE);
  if ((int)(heap_ptr = mem_sbrk(4 * WSIZE)) == -1) return -1;

  PUT(heap_ptr, PACK(0, 1));
  PUT(FTRP(heap_ptr), PACK(0, 1));

  char **epilog = NEXT_BLOCK_IN_HEAP(heap_ptr);
  PUT(epilog, PACK(0, 1));
  PUT(epilog, PACK(0, 1));

  heap_ptr += HDR_FTR_SIZE;

  char **new_block = extend_heap(CHUNK);
  if (new_block == NULL) return -1;
  add_block_into_list(new_block);
  return 0;
}

void *mm_malloc(size_t size) {
  if (size < CHUNK * WSIZE) size = pow2roundup(size);
  size_t words = ALIGN(size) / WSIZE, extend_size;
  char **bp;

  if (size == 0) return NULL;
  if ((bp = find_free_block(words)) == NULL) {
    extend_size = words > CHUNK ? words : CHUNK;
    if ((bp = extend_heap(extend_size)) == NULL) return NULL;
    alloc_free_block(bp, words);
    return bp + HDR_SIZE;
  }
  remove_block_from_list(bp);
  alloc_free_block(bp, words);
  return bp + HDR_SIZE;
}

void mm_free(void *ptr) {
  ptr -= WSIZE;
  size_t size = GET_SIZE(ptr);

  PUT(ptr, PACK(size, 0));
  PUT(FTRP(ptr), PACK(size, 0));

  ptr = coalesce(ptr);
  add_block_into_list(ptr);
}

int round_to_thousand(int x) {
  return x - x % 1000 + (x % 1000 >= 500 ? 1000 : 0);
}

static void *my_memmove(void *dest, const void *src, size_t n) {
  /* 避免内存有交叉 Copy from
   * https://stackoverflow.com/questions/13339582/why-is-linux-memmove-implemented-the-way-it-is
   */

  signed char operation;
  size_t end;
  size_t current;
  if (dest != src) {
    if (dest < src) {
      operation = 1;
      current = 0;
      end = n;
    } else {
      operation = -1;
      current = n - 1;
      end = -1;
    }

    for (; current != end; current += operation) {
      *(((unsigned char *)dest) + current) =
          *(((unsigned char *)src) + current);
    }
  }
  return dest;
}

void *mm_realloc_wrapped(void *ptr, size_t size, int buffer_size) {

  char **old = (char **)ptr - 1;
  char **bp = (char **)ptr - 1;
  size_t new_size = ALIGN(size) / WSIZE;
  size_t size_with_buffer = new_size + buffer_size;
  size_t old_size = GET_SIZE(bp);

  if (size_with_buffer == old_size && new_size <= size_with_buffer)
    return bp + HDR_SIZE;

  if (new_size > old_size) {
    if (GET_ALLOC(PREV_BLOCK_IN_HEAP(bp)) &&
        !GET_ALLOC(NEXT_BLOCK_IN_HEAP(bp)) &&
        GET_SIZE(NEXT_BLOCK_IN_HEAP(bp)) + old_size + HDR_FTR_SIZE >=
            size_with_buffer) {
      /* 判断是否可以和后面的块合并出足够大的块 */
      PUT(bp, PACK(old_size, 0));
      PUT(FTRP(bp), PACK(old_size, 0));
      bp = coalesce(bp);
      alloc_free_block(bp, size_with_buffer);
    } else if (!GET_ALLOC(PREV_BLOCK_IN_HEAP(bp)) &&
               GET_ALLOC(NEXT_BLOCK_IN_HEAP(bp)) &&
               GET_SIZE(PREV_BLOCK_IN_HEAP(bp)) + old_size + HDR_FTR_SIZE >=
                   size_with_buffer) {
      /* 判断是否可以和前面的块合并出足够大的块 */
      PUT(bp, PACK(old_size, 0));
      PUT(FTRP(bp), PACK(old_size, 0));
      bp = coalesce(bp);
      my_memmove(bp + 1, old + 1, old_size * WSIZE);
      alloc_free_block(bp, size_with_buffer);
    } else {
      bp = (char **)mm_malloc(size_with_buffer * WSIZE + WSIZE) - 1;
      if (bp == NULL) return NULL;
      my_memmove(bp + 1, old + 1, old_size * WSIZE);
      mm_free(old + 1);
    }
  }
  return bp + HDR_SIZE;
}

void *mm_realloc(void *ptr, size_t size) {
  if (ptr == NULL) return mm_malloc(size);
  if (size == 0) {
    mm_free(ptr);
    return NULL;
  }

  static int prev_size;
  int buffer_size, diff = abs(size - prev_size);

  buffer_size = (diff < CHUNK * WSIZE && diff % pow2roundup(diff))
                    ? pow2roundup(diff)
                    : round_to_thousand(size);

  void *result = mm_realloc_wrapped(ptr, size, buffer_size);
  prev_size = size;
  return result;
}

static void check_blocks_in_list_marked_free() {
  int i;
  char **bp;
  for (i = 0; i < POWER_LIMIT; ++i) {
    for (bp = (char **)GET_LIST_PTR(i); bp; bp = (char **)GET_SUCC(bp)) {
      /* 检测空闲列表中的每个块是否都标识为free */
      assert(GET_ALLOC(bp) == 0);
    }
  }
  printf("check_blocks_in_list_marked_free passed.\n");
}

static void check_contiguous_free_block_coalesced() {
  char **bp = heap_ptr;

  while (!GET_ALLOC(bp) && GET_SIZE(bp)) { // Haven't hit epilog
    assert(GET_ALLOC(bp) || GET_ALLOC(NEXT_BLOCK_IN_HEAP(bp)));
    bp = NEXT_BLOCK_IN_HEAP(bp);
  }

  printf("check_contiguous_free_block_coalesced passed.\n");
}

int mm_check() {
  /* printf("RUNNING MM CHECK.\n"); */
  /* check_blocks_in_list_marked_free(); */
  /* check_contiguous_free_block_coalesced(); */
  /* printf("MM CHECK FINISHED SUCCESSFUL.\n\n"); */
  return 1;
}
