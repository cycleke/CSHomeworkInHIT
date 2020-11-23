#include <stdio.h>

int is_little_endian() {
  unsigned int x, *p;
  x = 0;
  p = &x;
  *(char*) p = 0x22;
  return x == 0x22;
}

int main(int argc, char *argv[]) {
  puts(is_little_endian() ? "Little Endian" : "Big Endian");
  return 0;
}
