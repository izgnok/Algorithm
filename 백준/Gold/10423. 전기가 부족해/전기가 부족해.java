import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static List<List<Node>> graph;
    static List<Integer> start;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        start = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) start.add(Integer.parseInt(st.nextToken()));

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        int result = prim();
        sb.append(result).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        for (int s : start) pq.add(new Node(s, 0));
        boolean[] visit = new boolean[N + 1];

        int sum = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visit[node.vertex]) continue;
            visit[node.vertex] = true;
            sum += node.cost;
            if (N == ++count) break;

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