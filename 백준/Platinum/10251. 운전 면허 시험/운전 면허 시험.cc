#include <iostream>
#include <fstream>
#include <algorithm>
using namespace std;

typedef struct Nav {
    int right = -1;
    int down = -1;
} Nav;

Nav DP[100][100][200];
int down_[100][100];
int right_[100][100];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int T;
    cin >> T;
    while(T>0) {
        int N,M,L,G;
        cin >> N >> M >> L >> G;
        
        for(int i=0; i<N; i++) {
            for(int j=1; j<M; j++) {
                cin >> right_[i][j];
            }
        }
        for(int i=1; i<N; i++) {
            for(int j=0; j<M; j++) {
                cin >> down_[i][j];
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                for(int k=0; k<200; k++) {
                    if(i+j<=k && k!=0) break;
                    if(i==0 && j==0) {
                        DP[0][0][0].right = G;
                        DP[0][0][0].down = G;
                        break;
                    }
                    else if(i==0) DP[0][j][k].right = DP[0][j-1][k].right - right_[0][j];
                    else if(j==0) DP[i][0][k].down = DP[i-1][0][k].down - down_[i][0];
                    else {
                        //좌측에서올떄
                        DP[i][j][k].right = max(DP[i][j-1][k].right - right_[i][j], DP[i][j-1][k-1].down - right_[i][j]);
                        
                        //위에서 올떄
                        DP[i][j][k].down= max(DP[i-1][j][k].down - down_[i][j], DP[i-1][j][k-1].right - down_[i][j]);
                    }
                }
            }
        }
        int result = -1;
        for(int k=0; k<N+M; k++) {
            if(DP[N-1][M-1][k].down >= 0 || DP[N-1][M-1][k].right >= 0) {
                result = (L * (N-1 + M-1)) + k;
                break;
            }
            else continue;
        }
        cout << result << endl;
        T--;
    }
    return 0;
}