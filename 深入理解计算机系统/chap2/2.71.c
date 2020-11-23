#include <stdio.h>

/*
  A. 当压缩的有符号字节为负数时，返回值是正数。
  B. function xbtye
 */

typedef unsigned packed_t;

int xbyte(packed_t word, int bytenum) {
  unsigned left_shift = (3 - bytenum) << 3;
  unsigned right_shift = 3 << 3;
  return (int)word << left_shift >> right_shift;
}

int main() {
  printf("%d\n", xbyte(0xffffffff, 0));
  return 0;
}
