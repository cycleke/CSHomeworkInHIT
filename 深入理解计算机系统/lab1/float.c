#include <stdio.h>

int main(int argc, char *argv[]) {
  float f;
  for (;;) {
    scanf("%f", &f);
    printf("%f\n", f);
    if (f == 0) break;
  }
}
