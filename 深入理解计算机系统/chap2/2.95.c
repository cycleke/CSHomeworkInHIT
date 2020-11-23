#include <assert.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef unsigned float_bits;

float_bits float_half(float_bits f) {
  unsigned sign = f >> 31;
  unsigned exp = f >> 23 & 0xFF;
  unsigned frac = f & 0x7FFFFF;
  if (exp == 0xFF) return f;

  if (exp == 0) {
    int add = (frac & 0x3) == 0x3;
    frac >>= 1;
    frac += add;
  } else if (exp == 1) {
    int add = (frac & 0x3) == 0x3;
    unsigned temp = exp << 23 | frac;
    temp >>= 1;
    temp += add;
    exp = temp >> 23 & 0xFF;
    frac = temp & 0x7FFFFF;
  } else {
    --exp;
  }
  return (sign << 31) | (exp << 23) | frac;
}

int main(int argc, char const *argv[]) {
  unsigned a, b, c;
  float f;
  for (a = 0; a < -1U; ++a) {
    b = float_half(a);
    memcpy(&f, &a, sizeof(float));
    if (isnan(f)) continue;
    f *= 0.5;
    memcpy(&c, &f, sizeof(unsigned));
    if (b != c) {
      printf("%u %u %f\n", a, c, f);
    }
  }
  b = float_half(a);
  memcpy(&f, &a, sizeof(float));
  if (!isnan(f)) {
    f *= 0.5;
    memcpy(&c, &f, sizeof(unsigned));
    if (b != c) {
      printf("%u %u %f\n", a, c, f);
    }
  }
  return 0;
}
