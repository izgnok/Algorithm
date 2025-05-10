class Solution {
    static long[] DP;
    
    public long solution(int n) {
        
        DP = new long[n+1];
        DP[0] = 1;
        DP[1] = 1;
        for(int i=2; i<=n; i++) {
            DP[i] = (DP[i - 1] + DP[i - 2]) % 1234567;
        }
        return DP[n];
    }
    
}