import java.util.*;

class Solution {
    static int N;
    static List<List<Integer>> graph;
    static int result;
    
    public int solution(int n, int[][] wires) {
        N = n;
        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        
        int size = wires.length;
        for(int i=0; i<size; i++) {
            int x = wires[i][0];
            int y = wires[i][1];
            
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        
        result = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i==j) continue;
                bfs(i, j);
            }
        }
        return result;
    }
    
    public void bfs(int start1, int start2) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[N+1];
        
        visit[start1] = true;
        visit[start2] = true;
        
        int count1 = 0;
        q.add(start1);
        while(!q.isEmpty()) {
            int num = q.poll();
            count1++;
            
            List<Integer> child = graph.get(num);
            int childSize = child.size();
            for(int i=0; i<childSize; i++) {
                int next = child.get(i);
                if(visit[next]) continue;
                q.add(next);
                visit[next] = true;
            }
        }
        
        int count2 = 0;
        q.add(start2);
        while(!q.isEmpty()) {
            int num = q.poll();
            count2++;
            
            List<Integer> child = graph.get(num);
            int childSize = child.size();
            for(int i=0; i<childSize; i++) {
                int next = child.get(i);
                if(visit[next]) continue;
                q.add(next);
                visit[next] = true;
            }
        }
        
        
        int curr = Math.abs(count1 - count2);
        if(result > curr) result = curr;
    }
}