import java.util.*;

class Solution {
       
    static int[] cost;
    static List<List<Integer>> graph;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] road: roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }
        
        
        cost = new int[n + 1];
        Arrays.fill(cost, -1);
        bfs(n, destination);
        
        int size = sources.length;
        int[] answer = new int[size];
        for(int i=0; i<size; i++) {
            answer[i] = cost[sources[i]];
        }
        return answer;
    }
    
    static void bfs(int n, int start) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visit = new boolean[n + 1];
        
        visit[start] = true;
        cost[start] = 0;
        q.add(new Node(start , 0));
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            
            for(int next: graph.get(node.num)) {
                if(visit[next]) continue;
                visit[next] = true;
                cost[next] = node.count + 1;
                q.add(new Node(next, node.count + 1));
            }
        }
    }
    
    static class Node {
        int num, count;
        
        Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}