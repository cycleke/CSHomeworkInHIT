#include <stdio.h>
#include <string.h>

int main() {
  int a = -1298376426;
  unsigned b;
  memcpy(&b, &a, sizeof(b));
  printf("%x\n", a);
  return 0;
}
