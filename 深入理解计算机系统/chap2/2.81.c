#include <assert.h>
#include <stdio.h>

/*
   A. function fooA
   B. function fooB
*/

int fooA(int k) {
  return ~((1 << k) - 1);
}

int fooB(int k, int j) {
  return ~fooA(k) << j;
}

int main() {
  assert(fooA(8) == 0xFFFFFF00);
  assert(fooB(8, 4) == 0xFF0);
  return 0;
}
