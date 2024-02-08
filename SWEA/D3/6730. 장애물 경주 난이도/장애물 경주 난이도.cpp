#include <iostream>
using namespace std;

int main() {
    int T;
    cin >> T;
    int test_case;
    for( test_case=1; test_case <= T; test_case++ ) {
        int N;
        cin >> N;
        int *arr = new int[N];
        for(int i=0; i<N; i++) {
            cin >> arr[i];
        }
        int up_max = 0;
        int down_max = 0;
        for(int i=0; i<N-1; i++)  {
            if(arr[i]>arr[i+1]) {
                if(down_max < arr[i] - arr[i+1]) down_max = arr[i] - arr[i+1];
            }
        	if(arr[i] < arr[i+1]) {
                if(up_max < arr[i+1] - arr[i]) up_max = arr[i+1] - arr[i];
        	}
  	   }
       cout << '#' << test_case << ' ' << up_max << ' ' << down_max << endl;
       delete [] arr;
    }
    return 0;
}