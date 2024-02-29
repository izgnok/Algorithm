#pragma warning(disable:4996)
#include<stdio.h>
#include<math.h>

int main(void) {
	long long N;

	scanf("%lld", &N);
	
    for(long long int i = 1; i < N; i++)
    {
        long long int sum = ( (long long int)pow(2, i+1 ) - 1 ) * ( i + 1 ) + i;
        if( N <= sum )
        {
            printf("%lld\n", i);
            break;
        }
    }  
	return 0;
}