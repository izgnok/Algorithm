#include <stdio.h>
#pragma warning(disable:4996)

int main() {
	
	int n;
	scanf("%d", &n);

	for (int i = 0; i < n; i++)
	{
		for (int j=i;j<n;j++)
		{
			printf("*");
		}
		printf("\n");
	}
	return 0;
}