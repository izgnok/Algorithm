#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    int T;
    cin >> T;
    int test_case = 1;
    while(test_case<=T) {
        int N, M;
        cin >> N >> M ;
        int arr[15][15];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                cin >> arr[i][j];
            }
        }
        int result =0;
        for(int i =0; i<N; i++) {
            for(int j=0; j<N; j++) {
                int tmp1 = arr[i][j];
                int tmp2 = arr[i][j]; 
                for(int k=1; k<M; k++) {
                    if(j+k<N) tmp1 += arr[i][j+k];
                    if(j-k>=0) tmp1 += arr[i][j-k];
                    if(i+k<N) tmp1 += arr[i+k][j];
                    if(i-k>=0) tmp1 += arr[i-k][j];
                    
                    if(j+k<N && i+k < N) tmp2 += arr[i+k][j+k];
                    if(j-k>=0 && i-k>=0) tmp2 += arr[i-k][j-k];
                    if(i-k>=0 && j+k <N) tmp2 += arr[i-k][j+k];
                    if(i+k<N && j-k>=0) tmp2+= arr[i+k][j-k];
                }
               result = max(result, max(tmp1,tmp2));
            }
        }
        cout << '#' << test_case << ' ' << result << endl;
        test_case++;
    }
}