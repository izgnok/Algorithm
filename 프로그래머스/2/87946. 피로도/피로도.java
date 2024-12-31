import java.util.*;

class Solution {
    static int answer;
    static int size;
    static boolean[] check;
    
    
    public int solution(int k, int[][] dungeons) {
        size = dungeons.length;
        check = new boolean[size];
        
        answer = 0;
        dfs(0, k, dungeons, 0);
        return answer;
    }
    
    static void dfs(int depth, int k, int[][] dungeons, int count) {
        if(depth == size) {
            if(answer < count) answer = count;
            return;
        }
        
        for(int i=0; i<size; i++) {
            if(check[i]) continue;
            check[i] = true;
            if(k >= dungeons[i][0]) {
                dfs(depth + 1, k - dungeons[i][1], dungeons, count + 1) ;
            }
            check[i] = false;
            dfs(depth + 1, k, dungeons, count);
        }
    }
}