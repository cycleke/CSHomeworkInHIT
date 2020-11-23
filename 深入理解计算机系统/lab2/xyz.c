#include <stdio.h>

const int x = -9999999999;

int main(int argc, char *argv[]) {
  float y = 999999999999999999;
  static char *z = "cycleke";

  printf("%d\n", x);
  printf("%f\n", y);
  printf("%s\n", z);
  return 0;
}
