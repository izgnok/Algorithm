import java.util.*;

class Solution {
    
    static int N;
    static List<List<Node>> graph;
    
    public int solution(int n, int[][] costs) {
        N = n;
        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] cost: costs) {
            graph.get(cost[0]).add(new Node(cost[1], cost[2]));
            graph.get(cost[1]).add(new Node(cost[0], cost[2]));
        }
        int answer = prim();
        return answer;
    }
    
    static public int prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        boolean[] visit = new boolean[N+1];
        pq.add(new Node(0, 0));
        
        
        int result = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visit[node.vertex]) continue;
            visit[node.vertex] = true;
            result += node.cost;
            
            List<Node> childs = graph.get(node.vertex);
            for(Node child: childs) {
                if(visit[child.vertex]) continue;
                pq.add(new Node(child.vertex, child.cost));
            }
        }
        return result;
    }
    
    
    static class Node{
        int vertex, cost;
        
        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}