#include <stdio.h>

int signed_high_prod(int x, int y) {
  long long res = 1LL * x * y;
  return res >> 32;
}

unsigned unsigned_high_prod(unsigned x, unsigned y) {
  unsigned x_high = (int)x >> 31;
  unsigned y_high = (int)y >> 31;
  unsigned res = signed_high_prod(x, y);
  return res + (x_high & y) + (x & y_high);
}

int main() {
  unsigned x, y;
  scanf("%u %u", &x, &y);
  printf("Right: %u\nMine:  %u\n", (unsigned)((1ULL * x * y) >> 32),
         unsigned_high_prod(x, y));
  return 0;
}
