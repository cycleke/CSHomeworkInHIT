#include <stdio.h>

int main() {
  int i;
  float f;
  for (i = 1; i < 100; ++i) {
    f = 1.0 * i / 100;
    if (f * 100 == i) {
      printf("%d\n", i);
    }
  }
  return 0;
}
