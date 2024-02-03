#include <stdio.h>
#pragma warning(disable:4996)
int main() {

	int n;
	scanf("%d", &n);

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < i; j++)
		{
			printf(" ");
		}
		for (int j = i; j < (n*2)-i-1; j++)
		{
			printf("*");
		}
		printf("\n");
	}
	for (int i = 0; i < n - 1; i++)
	{
		for (int j = i+2; j < n; j++)
		{
			printf(" ");
		}
		for (int j = 0 ; j<i+i+3;j++)
		{
			printf("*");
		}
		printf("\n");
	}
}