#include <stdio.h>
#include <string.h>

void byte_copy(float *p, unsigned byte) {
  memcpy(p, &byte, sizeof(float));
}

#define PRINT(x) printf(#x " = %.30g hex: %a\n", x, x)

int main() {
  float pos_zero = 0.0, neg_zero = -0.0;
  float pos_max, pos_min, pos_norm_min, inf, nan;
  byte_copy(&pos_max, 0x7F7FFFFF);
  byte_copy(&pos_min, 0x00000001);
  byte_copy(&pos_norm_min, 0x00800000);
  byte_copy(&inf, 0x7F800000);
  byte_copy(&nan, 0x7F800001);
  
  PRINT(pos_zero);
  PRINT(neg_zero);
  PRINT(pos_min);
  PRINT(pos_max);
  PRINT(pos_norm_min);
  PRINT(inf);
  PRINT(nan);

  return 0;
}
