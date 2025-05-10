import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<List<Node>> graph;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        while (M-- > 0) {
            boolean[] visit = new boolean[N + 1];
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(bfs(start, end)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs(int start, int end) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visit = new boolean[N + 1];
        q.add(new Node(start, 0));
        visit[start] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (Node child : graph.get(node.vertex)) {
                if (visit[child.vertex]) continue;
                if (child.vertex == end) {
                    return node.cost + child.cost;
                }
                visit[child.vertex] = true;
                q.add(new Node(child.vertex, node.cost + child.cost));
            }
        }
        return -1;
    }


    static class Node {
        int vertex, cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
