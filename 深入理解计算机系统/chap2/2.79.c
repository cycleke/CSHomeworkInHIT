#include <assert.h>
#include <stdio.h>

int divie_power2(int x, int k) {
  int sign = x >> 31;
  x += sign & ((1 << k) - 1);
  return x >> k;
}

int mul3div4(int x) { return divie_power2(x + (x << 1), 2); }

int main(int argc, char const *argv[]) {
  assert(mul3div4(12) == 9);
  assert(mul3div4(65) == 48);
  assert(mul3div4(-65) == -65 * 3 / 4);
  return 0;
}
