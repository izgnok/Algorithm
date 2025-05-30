import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        
        int INF = 987654321;
        int[] DP = new int[y + 1];
        Arrays.fill(DP, INF);
        DP[x] = 0;
        for(int i=x+1; i<=y; i++) {
            DP[i] = INF;
            if(i % 2 == 0) DP[i] = Math.min(DP[i], DP[i/2]);
            if(i % 3 == 0) DP[i] = Math.min(DP[i], DP[i/3]);
            if(i > n) DP[i] = Math.min(DP[i], DP[i - n]);
            if(DP[i] != INF) DP[i]++;
        }
        return DP[y] != INF ? DP[y] : -1;
    }
}