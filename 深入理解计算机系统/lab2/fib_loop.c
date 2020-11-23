#include <stdio.h>

#define integer unsigned long
#define FORMAT "%lu"
#define MAXN 100

int main() {
  int i;
  integer fib[MAXN] = {0, 1};
  for (i = 2; i < MAXN; ++i)
    fib[i] = fib[i - 1] + fib[i - 2];
  for (i = 0; i < MAXN; ++i)
    printf("%d " FORMAT "\n", i, fib[i]);
  return 0;
}
