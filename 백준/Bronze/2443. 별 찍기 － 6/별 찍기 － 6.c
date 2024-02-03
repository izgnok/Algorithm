#include <stdio.h>
#pragma warning(disable:4996)

int main() {
	
	int n,sum;
	scanf("%d", &n);

	sum = (n * 2) - 1;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < i; j++)
		{
			printf(" ");
		}
		for (int j=i+i; j<sum;j++)
		{
			printf("*");
		}
		printf("\n");
	}
	return 0;
}