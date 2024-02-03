#include <stdio.h>
#pragma warning(disable:4996)

int main() {
	
	int n;
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j <= i; j++)
		{
			printf("*");
		}
		for (int j = i; j < (n * 2)-i-2; j++)
		{
			printf(" ");
		}
		for (int j = 0; j <= i; j++)
		{
			printf("*");
		}
		printf("\n");
	}
	for (int i = 0; i < n; i++)
	{
		for (int j = i; j < n - 1; j++)
		{
			printf("*");
		}

		for (int j = 0; j <= i*2+1; j++)
		{
			printf(" ");
		}
		for (int j = i; j < n - 1; j++)
		{
			printf("*");
		}
		if (i==n-1 ) break;
		printf("\n");
	}
	return 0;
}