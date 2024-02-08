#include <iostream>
using namespace std;

bool DP_Row[100][100][101];
bool DP_Col[100][100][101];

int main() {
    int test_case = 0;
        while(1) {
            cin >> test_case;
            char arr[100][100];
            for(int i=0; i<100; i++) {
                for(int j=0; j<100; j++) cin >> arr[i][j];
            }
            
            int max = 1;
            for(int k=1; k<=100; k++) {
                for(int i=0; i<100; i++) {
                    for(int j=0; j<100; j++) {
                        if(k==1) DP_Row[i][j][1] = true;
                        else if(k==2) {
                            if( 99 >= j+1 ) {
                                if( arr[i][j] == arr[i][j+1] ) {
                                    DP_Row[i][j][2] = true;
                                    max = 2;
                                  }
                                else DP_Row[i][j][2] = false;
                            }
                            else DP_Row[i][j][2] = false;
                        }
                        else {
                            if( 99 >= j+k-1 ) {
                                if( arr[i][j] == arr[i][j+k-1] ) {
                                    if ( DP_Row[i][j+1][k-2] )  {
                                        DP_Row[i][j][k] = true;
                                        if(max < k) max = k;
                                    }
                                    else DP_Row[i][j][k] = false;
                                }
                                else DP_Row[i][j][k] = false;
                            }
                            else DP_Row[i][j][k] = false;
                        }
                    }
                }
            }
            for(int k=1; k<=100; k++) {
                for(int i=0; i<100; i++) {
                    for(int j=0; j<100; j++) {
                        if(k==1) DP_Col[i][j][1] = true;
                        else if(k==2) {
                            if( 99 >= i+1 ) {
                                if( arr[i][j] == arr[i+1][j] ) {
                                    DP_Col[i][j][2] = true;
                                    if(max <2 ) max = 2;
                                }
                                else DP_Col[i][j][2] = false;
                            }
                            else DP_Col[i][j][2] = false;
                        }
                        else {
                            if( 99 >= i+k-1) {
                                if( arr[i][j] == arr[i+k-1][j] ) {
                                    if ( DP_Col[i+1][j][k-2])  {
                                        DP_Col[i][j][k] = true;
                                        if(max < k) max = k;
                                    }
                                    else DP_Col[i][j][k] = false;
                                }
                                else DP_Col[i][j][k] = false;
                            }
                            else DP_Col[i][j][k] = false;
                        }
                    }
                }
            }
            cout << '#' << test_case << ' ' << max << endl;
            if(test_case == 10) break;
        }
    return 0;
}
