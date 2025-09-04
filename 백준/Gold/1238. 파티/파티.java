import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, target;
    static List<List<Node>> graph;

    static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        cost = new int[N + 1][N + 1];

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            Arrays.fill(cost[i], 987654321);
            cost[i][i] = 0;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, cost));
        }

        for (int i = 0; i <= N; i++) {
            dijkstra(i, target);
            dijkstra(target, i);
        }

        int max = 0;
        for (int i = 1; i <= N; i++) max = Math.max(max, cost[i][target] + cost[target][i]);

        sb.append(max).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, 0));
        boolean[] visit = new boolean[N + 1];

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.vertex == end) return;
            if (visit[node.vertex]) continue;
            visit[node.vertex] = true;

            for (Node child : graph.get(node.vertex)) {
                if (visit[child.vertex]) continue;
                if (cost[start][child.vertex] <= node.cost + child.cost) continue;
                cost[start][child.vertex] = node.cost + child.cost;
                pq.add(new Node(child.vertex, cost[start][child.vertex]));
            }
        }
    }

    static class Node {
        int vertex, cost;

        public Node(int v, int c) {
            vertex = v;
            cost = c;
        }
    }

}