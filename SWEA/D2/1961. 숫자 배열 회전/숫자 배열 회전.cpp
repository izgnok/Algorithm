#include <iostream>
using namespace std;
int main() {
    int T;
    cin >> T;
    int test_case = 1;
    while(test_case<=T) {
        int N;
        cin >> N;
        int arr[7][7];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                cin >> arr[i][j];
            }
        }
        cout << '#' << test_case << endl;
        test_case++;
        
  		int i_90 = N-1;
        int j_90 = 0;
        int i_180 = N-1;
        int j_180 = N-1;
        int i_270 = 0;
        int j_270 = N-1;
        for(int i=0; i<N; i++){
        	for(; i_90>=0 ; i_90--) {
                cout << arr[i_90][j_90];
            }
            
            cout << ' ';
            for(; j_180>=0 ; j_180--) {
                cout << arr[i_180][j_180];
            }
            cout << ' ';
            for(;i_270<N; i_270++) {
                cout << arr[i_270][j_270];
            }
            cout << endl;
            i_90 =N-1;
            j_90++;
            i_180--;
            j_180 =N-1;
            i_270 =0;
            j_270--;
        }
    }
    return 0;
}