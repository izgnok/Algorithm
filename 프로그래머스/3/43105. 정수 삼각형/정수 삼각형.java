class Solution {
    public int solution(int[][] triangle) {
        int N = triangle.length;
        int[][] DP = new int[N][N];
        
        DP[0][0] = triangle[0][0];
        for(int i=1; i<N; i++) {
            for(int j=0; j<=i; j++) {
                if(j==0) DP[i][j] = DP[i-1][j];
                else if(j==i) DP[i][j] = DP[i-1][j-1];
                else DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-1]);
                
                DP[i][j] += triangle[i][j];
            }
        }
        
        int result = DP[N-1][0];
        for(int j=1; j<N; j++) {
            if(result < DP[N-1][j]) result = DP[N-1][j];
        }
        return result;
    }
}