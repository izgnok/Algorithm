#include <iostream>
using namespace std;

int main() {
    int T;
    cin >> T;
    int test_case = 1;
    while(test_case<=T) {
        int arr[9][9];
        int result = 1;
        for(int i=0; i<9; i++){
            int garo_num[10] ={0,};
            for(int j=0; j<9; j++) {
                int k;
                cin >> k;
                if(garo_num[k] == 0) {
                    garo_num[k] = 1;
                    arr[i][j] = k;
                }
                else {
                    result = 0;
                }
            }
        }
        for(int j=0; j<9; j++) {
            if(result==0) break;
            int sero_num[10] = {0,};
            for(int i=0; i<9; i++) {
                int k = arr[i][j];
                 if(sero_num[k] == 0) {
                    sero_num[k] = 1;
                }
                else {
                    result = 0;
                    break;
                }
            }
        }    
        int i=0;
        int j=0;
        while(true) {
            if(result==0) break;
        	int sq_num[10] = {0,};
			sq_num[arr[i][j]] = 1;
            sq_num[arr[i][j+1]] = 1;
            sq_num[arr[i][j+2]] = 1;
            sq_num[arr[i+1][j]] = 1;
            sq_num[arr[i+1][j+1]] = 1;
            sq_num[arr[i+1][j+2]] = 1;
            sq_num[arr[i+2][j]] = 1;
            sq_num[arr[i+2][j+1]] = 1;
            sq_num[arr[i+2][j+2]] = 1;
            for(int k=1; k<10; k++) {
                if(sq_num[k] == 0) {
                    result = 0;
                    break;
                }
            }            
            if(i>=6 && j>=6) break;
            if(i==6) {
                i=0;
                j+=3;
            }
            else {
                i+=3;
            }
        }
        cout << '#' << test_case << ' ' << result << endl;
        test_case++;
    }
    return 0;                                 
}