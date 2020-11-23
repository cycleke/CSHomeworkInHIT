#include <stdio.h>

void bubble(long *data , long count) {
  long *i, *last;
  for (last = data + count - 1; last > data; --last)
    for (i = data; i < last; ++i)
      if (*(i + 1) < *i) {
        long t = *(i + 1);
        *(i + 1) = *i;
        *i = t;
      }
}


int main() {
  long a[4] = {4, 2, 3, 1};
  bubble(a, 4);
  for (int i = 0; i < 4; ++i) printf("%ld ", a[i]);
  return 0;
}
