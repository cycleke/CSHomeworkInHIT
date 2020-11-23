#include <stdio.h>
#include <stdlib.h>

#define MAX_LEN 16

void print_buffer(char *s, int size) {
  int i;
  for (i = 0; i < size; ++i) {
    switch (s[i]) {
    case '\t':
      printf("\\t");
      break;
    case '\n':
      printf("\\n");
      break;
    case '\r':
      printf("\\r");
      break;
    default:
      putchar(s[i]);
    }
    putchar('\t');
  }
  puts("");
  for (i = 0; i < size; ++i) {
    printf("%06X\t", (int)s[i]);
  }
  puts("");
}

int main() {
  char ch, buffer[MAX_LEN];
  int len;
  printf("hellolinux.c(l/L) or hellowin.c(w/W)");
  scanf("%c", &ch);
  if (ch == 'l' || ch == 'L') {
    freopen("hellolinux.c", "r", stdin);
  } else if (ch == 'w' || ch == 'W') {
    freopen("hellowin.c", "r", stdin);
  } else {
    puts("Wrong Input");
    exit(0);
  }

  len = 0;
  while (scanf("%c", &ch) != EOF) {
    buffer[len++] = ch;
    if (len == MAX_LEN) {
      print_buffer(buffer, len);
      len = 0;
    }
  }
  if (len > 0) {
    print_buffer(buffer, len);
  }

  return 0;
}
