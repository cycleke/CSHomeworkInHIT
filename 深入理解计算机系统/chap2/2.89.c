#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
  srand(time(NULL));
  int x = random();
  int y = random();
  int z = random();
  double dx = (double)x;
  double dy = (double)y;
  double dz = (double)z;

  printf("%lf %lf %lf\n", dx, dy, dz);
  printf("%d\n", (float)x == (float)dx);
  printf("%d\n", dx - dy == (double)(x - y));
  printf("%d\n", (dx + dy) + dz == dx + (dy + dz));
  printf("%d\n", (dx * dy) * dz == dx * (dy * dz));
  printf("%d\n", dx / dx == dz / dz);
  return 0;
}