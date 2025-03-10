import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int V, E;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(v1).add(new Node(v2, cost));
            graph.get(v2).add(new Node(v1, cost));
        }

        int result = prim();
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static public int prim() {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        boolean[] visit = new boolean[V + 1];
        pq.add(new Node(1, 0));

        int sum = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visit[node.vertex]) continue;
            visit[node.vertex] = true;
            sum += node.cost;

            for (Node child : graph.get(node.vertex)) {
                if (visit[child.vertex]) continue;
                pq.add(new Node(child.vertex, child.cost));
            }
        }
        return sum;
    }

    static class Node {
        int vertex, cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

}
