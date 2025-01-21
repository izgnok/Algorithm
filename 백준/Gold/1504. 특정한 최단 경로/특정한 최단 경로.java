import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static List<List<Node>> graph = new ArrayList<>();
    static int[][] cost1;
    static int[][] cost2;
    static int N, E;

    static int INF = 987654321;
    static int answer = INF;

    static int v1, v2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        cost1 = new int[N+1][N+1];
        cost2 = new int[N+1][N+1];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(cost1[i], INF);
            Arrays.fill(cost2[i], INF);
            graph.add(new ArrayList<>());
        }

        while(E-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(x).add(new Node(y, c));
            graph.get(y).add(new Node(x, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        Solution();

        if(answer == INF) answer = -1;
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void Solution() {
        // s -> v1 -> v2 -> e
        dijkstra(1, v1, cost1);
        dijkstra(v1, v2, cost1);
        dijkstra(v2, N, cost1);
        int sum1 = INF;
        if(cost1[1][v1] != INF && cost1[v1][v2] != INF && cost1[v2][N] != INF) {
            sum1 = cost1[1][v1] + cost1[v1][v2] + cost1[v2][N];
        }

        // s -> e -> v2 -> v1
        dijkstra(1, v2, cost2);
        dijkstra(v2, v1, cost2);
        dijkstra(v1, N, cost2);
        int sum2 = INF;
        if(cost2[1][v2] != INF && cost2[v2][v1] != INF && cost2[v1][N] != INF) {
            sum2 = cost2[1][v2] + cost2[v2][v1] + cost2[v1][N];
        }
        answer = Math.min(sum1, sum2);
    }

    static void dijkstra(int start, int end, int[][] cost) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        boolean[] visit = new boolean[N+1];
        pq.add(new Node(start, 0));
        cost[start][start] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(node.vertex == end) return;
            if(visit[node.vertex]) continue;
            visit[node.vertex] = true;
            int c = node.cost;

            List<Node> children = graph.get(node.vertex);
            for (Node child : children) {
                if(visit[child.vertex]) continue;
                if(c + child.cost < cost[start][child.vertex]) {
                    cost[start][child.vertex] = c + child.cost;
                    pq.add(new Node(child.vertex, c + child.cost));
                }
            }
        }
    }

    static class Node {
        int vertex, cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Vertex: " + this.vertex + ", Cost: " + this.cost;
        }
    }
}