#include <stdio.h>

#define PRINT(x, format) printf(#x " = " format "\n", x)

int main() {
  float a = 0x1.101000p+0;
  float b = 0x1.101001p+0;
  float c = 0x1.10101011p+0;
  float d = 0x1.101011p+0;

  PRINT(a, "%a");
  PRINT(b, "%a");
  PRINT(c, "%a");
  PRINT(d, "%a");
  return 0;
}
