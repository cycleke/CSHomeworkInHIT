#include <stdio.h>

void show_byte(char *start, int len) {
  int i;
  for (i = 0; i < len; ++i)
    printf("%#.2X ", (unsigned char)start[i]);
  puts("");
}

int main() {
  float x = -10.1;
  show_byte((char*)&x, sizeof(x));
  return 0;
}
