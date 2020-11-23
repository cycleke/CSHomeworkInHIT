#include <stdio.h>

float cs_atof(const char *s) {
  float x = 0, d = 0.1;
  int sign = 0;
  char ch = *s++;
  while (ch < '0' || ch > '9') {
    if (ch == EOF || !ch) return 0;
    if (ch == '.') break;
    sign |= ch == '-';
    ch = *s++;
  }
  while (ch >= '0' && ch <= '9') {
    x = x * 10 + ch - '0';
    ch = *s++;
  }
  if (ch == '.') {
    for (ch = *s++; ch >= '0' && ch <= '9'; ch = *s++) {
      x += d * (ch - '0');
      d *= 0.1;
    }
  }
  return sign ? -x : x;
}

int main() {
  float f;
  char s[128];
  scanf("%s", s);
  f = cs_atof(s);
  printf("%f\n", f);
  return 0;
}
