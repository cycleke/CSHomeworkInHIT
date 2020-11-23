#include <stdio.h>
#include <math.h>

// return the length of s
int cs_itoa(int x, char *s) {
  if (x == 0) {
    *s = '0';
    return 1;
  }
  char *p = s;
  if (x < 0) {
    *p++ = '-';
    x = -x;
  }

  char buf[20], *p_buf = buf;
  while (x) {
    *p_buf++ = x % 10 + '0';
    x /= 10;
  }
  while (p_buf != buf)
    *p++ = *--p_buf;
  *p++ = 0;
  return p - s;
}

int cs_ftoa(float x, int precision, char *s) {
  int cnt, t;
  char *p;
  p = s + cs_itoa((int)x, s) - 1;
  *p++ = '.';
  x -= (int)x;
  x += 5 * pow(10, -precision - 1);
  for (cnt = 0; cnt < precision; ++cnt) {
    x *= 10;
    t = x;
    *p++ = t + '0';
    x -= t;
  }
  *p++ = 0;
  return p - s;
}

int main() {
  float x;
  char s[128];
  scanf("%f", &x);
  cs_ftoa(x, 5, s);
  puts(s);
  return 0;
}
