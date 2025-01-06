import java.util.*;

class Solution {
    static int N;
    static int answer;
    static boolean[] visit;
    
    public int solution(String begin, String target, String[] words) {
        N = words.length;
        answer = Integer.MAX_VALUE;
        visit = new boolean[N];
        dfs(0, begin, target, words);
        if(answer == Integer.MAX_VALUE) answer = 0;
        return answer;
    }
    
    static void dfs(int depth, String cur, String target, String[] words) {
        if(cur.equals(target)) {
            answer = Math.min(answer, depth);
            return;
        }
        if(depth == N) {
            return;
        }
        
        
        for(int i=0; i<N; i++) {
            if(visit[i]) continue;
            
            int count = 0;
            for(int j=0; j< words[i].length(); j++) {
                if(cur.charAt(j) != words[i].charAt(j)) {
                    count++;
                }
                if(count > 1) break;
            }
            if(count > 1) continue;
            visit[i] = true;
            dfs(depth + 1, words[i], target, words);
            visit[i] = false;
        }
    }
}