#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {
	int x;
	float y;
	char z[256];

	assert(argc == 4);

	x = atoi(argv[1]);
	y = atof(argv[2]);
	memcpy(z, argv[3], 256);

	printf("x = %d\n", x);
	printf("y = %f\n", y);
	printf("z = %s\n", z);
	return 0;
}