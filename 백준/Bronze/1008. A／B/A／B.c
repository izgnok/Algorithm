#include <stdio.h>
#pragma warning(disable:4996)
int main() {


	int A, B;
	scanf("%d %d", &A, &B);

	double sum;

	sum = (double)A / B;

	printf("%.9f", sum);
}