#include <stdio.h>

int utf8len(const char *s) {
  int cnt = 0;
  for(; *s; ++s)
    if (((*s) & 0xC0) != 0x80) ++cnt;
  return cnt;
}

int main() {
  char str[100];
  scanf("%s", str);
  printf("%d\n", utf8len(str));
  return 0;
}
