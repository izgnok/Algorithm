import java.util.*;

class Solution {
    
    static List<List<Integer>> graph;
    static int N;
    static int[] win;
    static int[] lose;
    
    public int solution(int n, int[][] results) {
        N = n;
        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] result: results) {
            graph.get(result[0]).add(result[1]);
        }
        
        win = new int[N+1];
        lose = new int[N+1];
        for(int i=1; i<=N; i++) {
            bfs(i);
        }
        
        int answer = 0;
        for(int i=1; i<=N; i++) {
            if(win[i] + lose[i] == N - 1) answer++;
        }
        
        return answer;
    }
    
    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[N+1];
        q.add(start);
        visit[start] = true;
        
        while(!q.isEmpty()) {
            int num = q.poll();
            
            List<Integer> list = graph.get(num);
            for(int child: list) {
                if(visit[child]) continue;
                visit[child] = true;
                q.add(child);
                win[start]++;
                lose[child]++;
            }
        }
    }
}