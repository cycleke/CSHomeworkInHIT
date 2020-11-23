#include <stdio.h>

char cstr[100] = "cycleke";
char *pstr = "cycleke";

int main() {
  puts(cstr);
  puts(pstr);
  pstr[0] = '2';
  return 0;
}
