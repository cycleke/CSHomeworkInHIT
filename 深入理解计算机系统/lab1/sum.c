#include <stdio.h>

int sum(int a[], unsigned len) {
  int i, sum = 0;
  for (i = 0; i < len - 1; ++i)
    sum += a[i];
  return sum;
}

int main() {
  int a[3] = {1, 2, 3};
  printf("%d\n", sum(a, 0));
  return 0;
}
