#include <stdio.h>

int cpu_word_size() {
  char x;
  return 8 * sizeof(&x);
}

int main(int argc, char *argv[]) {
  printf("%d\n", cpu_word_size());
  return 0;
}
