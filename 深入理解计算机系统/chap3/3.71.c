#include <assert.h>
#include <stdio.h>

#define BUFFER_SIZE

int good_echo() {
  char buffer[BUFFER_SIZE];
  if (fgets(buffer, BUFFER_SIZE, stdin) == NULL) {
    return EOF;
  }
  puts(buffer);
}

int main() {
  good_echo();
  return 0;
}
