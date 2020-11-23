#include <stdio.h>

unsigned connect(unsigned x, unsigned y) {
  return (x & 0xFF) | (y & 0xFFFFFF00);
}

int main() {
  unsigned x = 0x89ABCDEF, y = 0x76543210;

  printf("Generate 0x%X.\n", connect(x, y));
  return 0;
}
