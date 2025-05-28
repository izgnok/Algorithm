import java.util.*;

class Solution {
    int solution(int[][] land) {
        int n = land.length;
        int[][] DP = new int[n][4];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<4; j++) {
                if(i==0) {
                    DP[i][j] = land[i][j];
                    continue;
                }
                DP[i][j] = Math.max(Math.max(DP[i-1][(j+1) % 4], DP[i-1][(j+2) % 4]), DP[i-1][(j+3) % 4]) + land[i][j];
            }
        }
        return Math.max(Math.max(Math.max(DP[n-1][0], DP[n-1][1]), DP[n-1][2]), DP[n-1][3]);
    }
}