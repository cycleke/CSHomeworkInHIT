#include <stdio.h>

unsigned srl(unsigned x, int k) {
  unsigned xsra = (int)x >> k;
  unsigned w = sizeof(int) << 3;
  unsigned mask = ((1U << k) - 1) << (w - k);
  return xsra & ~mask;
}

int sra(int x, int k) {
  unsigned xsrl = (unsigned)x >> k;
  unsigned w = sizeof(int) << 3;
  unsigned sign = 1 << (w - k - 1);
  sign &= xsrl;
  sign = ~(sign - 1);
  return xsrl | sign;
}

int main() {
  int type, x1, k;
  unsigned x0;

  for (;;) {
    scanf("%d", &type);
    if (type == 0) {
      scanf("%u %d", &x0, &k);
      printf("Right: %#.8X\nMine:  %#.8X\n", x0 >> k, srl(x0, k));
    } else if (type == 1) {
      scanf("%d %d", &x1, &k);
      printf("Right: %#.8X\nMine:  %#.8X\n", x1 >> k, sra(x1, k));
    } else {
      break;
    }
  }
  return 0;
}
