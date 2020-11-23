#include <stdio.h>

int cs_atoi(const char *s) {
  int x, sign = 0;
  char ch = *s++;
  while (ch < '0' || ch > '9') {
    if (ch == EOF) return 0;
    sign |= ch == '-';
    ch = *s++;
  }
  x = ch - '0';
  for (ch = *s++; ch >= '0' && ch <= '9'; ch = *s++)
    x = x * 10 + ch - '0';
  return sign ? -x : x;
}

int main() {
  int x;
  char s[128];
  scanf("%s", s);
  x = cs_atoi(s);
  printf("%d\n", x);
  return 0;
}
