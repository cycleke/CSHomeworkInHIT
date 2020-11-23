#include <assert.h>
#include <stdio.h>

int divie_power2(int x, int k) {
  int sign = x >> 31;
  x += sign & ((1 << k) - 1);
  return x >> k;
}

int main() {
  assert(divie_power2(100, 2) == 100 / 4);
  assert(divie_power2(199, 2) == 199 / 4);
  assert(divie_power2(-198, 2) == -198 / 4);
  assert(divie_power2(-197, 2) == -197 / 4);
  return 0;
}
