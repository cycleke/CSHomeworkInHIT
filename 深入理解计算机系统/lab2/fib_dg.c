#include <stdio.h>

#define integer int
#define FORMAT "%d"

integer fib(integer n) {
  if (n <= 1) return n;
  return fib(n - 1) + fib(n - 2);
}

int main() {
  integer n;
  scanf(FORMAT, &n);
  printf(FORMAT "\n", fib(n));
  return 0;
}
