import java.util.*;

class Solution {
    
    static List<List<Node>> graph;
    static int N, K;
    
    public int solution(int n, int[][] road, int k) {
        
        N = n;
        K = k;
        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] arr: road) {
            graph.get(arr[0]).add(new Node(arr[1], arr[2]));
            graph.get(arr[1]).add(new Node(arr[0], arr[2]));
        }
        
        return dijkstra();
    }
    
    static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        }); 
        pq.add(new Node(1, 0));
        
        boolean[] visit = new boolean[N + 1];
        
        int[] costs = new int[N + 1];
        Arrays.fill(costs, 987654321);
        costs[1] = 0;
        
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visit[node.vertex]) continue;
            visit[node.vertex] = true;
            
            for(Node child: graph.get(node.vertex)) {
                if(visit[child.vertex]) continue;
                if(costs[child.vertex] <= node.cost + child.cost) continue;
                costs[child.vertex] = node.cost + child.cost;
                pq.add(new Node(child.vertex, costs[child.vertex]));
            }
        }
        
        int count = 0;
        for(int i=1; i<=N; i++) {
            if(costs[i] <= K) count++;
        }
        return count;
    }
    
    static class Node {
        int vertex, cost;
        
        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}