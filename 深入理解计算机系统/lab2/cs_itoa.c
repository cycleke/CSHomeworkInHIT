#include <stdio.h>

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

int main() {
  int x, len;
  char s[128];
  scanf("%d", &x);
  len = cs_itoa(x, s);
  printf("%s\nlen=%d\n", s, len);
  return 0;
}
