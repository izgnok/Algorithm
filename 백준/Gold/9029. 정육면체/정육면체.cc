#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;

int Wood[201][201][201];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N;
    cin >> N;
    while(N>0) {
        int x,y,z;
        cin >> x >> y >> z;
        
        memset(Wood, 0, sizeof Wood);
        for(int i=1; i<=x; i++) {
            for(int j=1; j<=y; j++) {
                for(int k=1; k<=z; k++) {
                    if(Wood[i][j][k] != 0) continue;
                    else {
                        if(j%i==0 && k%i==0) {
                            Wood[i][j][k] = (j/i) * (k/i);
                        }
                        else if(i%j==0 && k%j==0) {
                            Wood[i][j][k] = (i/j) * (k/j);
                        }
                        else if(i%k==0 && j%k==0) {
                            Wood[i][j][k] = (i/k) * (j/k);
                        }
                        else {
                            int min = 10000000;
                            for(int t=1; t<= i/2; t++) {
                                if(min > Wood[i-t][j][k] + Wood[t][j][k]) {
                                    min = Wood[i-t][j][k] + Wood[t][j][k];
                                }
                            }
                            for(int t=1; t<= j/2; t++) {
                                if(min > Wood[i][j-t][k] + Wood[i][t][k]) {
                                    min = Wood[i][j-t][k] + Wood[i][t][k];
                                }
                            }
                            for(int t=1; t<= k/2; t++) {
                                if(min > Wood[i][j][k-t] + Wood[i][j][t]) {
                                    min = Wood[i][j][k-t] + Wood[i][j][t];
                                }
                            }
                            Wood[i][j][k] = min;
                            Wood[i][k][j] = min;
                            Wood[j][i][k] = min;
                            Wood[j][k][i] = min;
                            Wood[k][i][j] = min;
                            Wood[k][j][i] = min;
                        }
                    }
                }
            }
        }
        //출력
        cout << Wood[x][y][z] << endl;
        N--;
    }
    return 0;
}