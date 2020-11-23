#include <stdio.h>

int main() {
  float x = 0 / 0x1p-149;
  printf("0 / min float = %f\n", x);
  return 0;
}
