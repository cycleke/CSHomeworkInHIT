#include <stdio.h>

/*
  A. 右移的位数应该非负且不超过左端类型的位数。
  B. function int_size_is_32
  C. function int_size_is_32_for_16bit
 */

int int_size_is_32() {
  int set_msb = 1 << 31;
  int beyond_msb = set_msb << 1;
  return set_msb && !beyond_msb;
}

int int_size_is_32_for_16bit() {
  int set_msb = 1 << 1;
  set_msb <<= 15;
  set_msb <<= 15;
  int beyond_msb = set_msb << 1;
  return set_msb && !beyond_msb;
}

int main() {
  puts(int_size_is_32() ? "Ths int is 32-bit." : "The int is not 32-bit.");
  return 0;
}
