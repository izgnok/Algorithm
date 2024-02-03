#include <iostream>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int N;
    cin >> N;
    
    int *dp = new int[N+1];
    
    dp[0] = -1;
    dp[1] = 0;
    dp[2] = 1;
    dp[3] = 1;
    
    for(int i=4; i<=N; i++) {
        if(i%3 == 0 && i%2 == 0) {
            dp[i] = min(min(dp[i/2],dp[i/3]),dp[i-1]) + 1;
        }
        else if(i%3 == 0) {
            dp[i] = min(dp[i/3],dp[i-1]) + 1;
        }
        else if(i%2 == 0) {
            dp[i] = min(dp[i/2],dp[i-1]) + 1;
        }
        else {
            dp[i] = dp[i-1] + 1;
        }
    }
    
    cout << dp[N] << endl;
    delete[] dp;
    return 0;
}
