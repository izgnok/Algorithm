#include <stdio.h>
#pragma warning(disable:4996)

int main() {
	
	int n;
	scanf("%d", &n);

	
	for (int i = 0; i < n; i++)
	{
		for (int j = i; j < n - 1; j++)
		{
			printf(" ");
		}
		for (int j = i+i; j >= 0; j--)
		{
			printf("*");
		}
		printf("\n");
	}
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j <= i; j++)
		{
			printf(" ");
		}
		for (int j = i+i; j < (n*2)-3; j++)
		{
			printf("*");
		}
		if ((n * 2) - 3 <= i + i) break;
		printf("\n");
	}
	return 0;
}