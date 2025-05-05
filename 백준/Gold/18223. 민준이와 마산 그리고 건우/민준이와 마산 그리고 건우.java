import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int V, E, P;

    static List<List<Node>> graph;
    static int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        int[] costA = new int[V + 1];
        int[] costB = new int[V + 1];
        Arrays.fill(costA, INF);
        Arrays.fill(costB, INF);

        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        dijkstra(1, costA);
        dijkstra(P, costB);
        if (costA[V] == costA[P] + costB[V]) sb.append("SAVE HIM");
        else sb.append("GOOD BYE");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dijkstra(int start, int[] cost) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        boolean[] visit = new boolean[V + 1];
        cost[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.v == V) return;
            if (visit[node.v]) continue;
            visit[node.v] = true;

            for (Node child : graph.get(node.v)) {
                if (visit[child.v]) continue;
                if (cost[child.v] > node.cost + child.cost) {
                    cost[child.v] = node.cost + child.cost;
                    pq.add(new Node(child.v, cost[child.v]));
                }
            }
        }
    }

    static class Node {
        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    ", cost=" + cost +
                    '}';
        }
    }

}
