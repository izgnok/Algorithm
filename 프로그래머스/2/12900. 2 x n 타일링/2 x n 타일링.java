class Solution {
    public int solution(int n) {
        
        int[] DP = new int[n+1];
        DP[0] = 0;
        DP[1] = 1;
        DP[2] = 2;
        
        for(int i=3; i<=n; i++) {
            DP[i] = (DP[i-1] + DP[i-2]) % 1000000007;
        }
        
        return DP[n];
    }
}