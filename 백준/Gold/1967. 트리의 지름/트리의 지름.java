import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N;

    static List<List<Node>> graph;
    static int max;
    static int start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

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

        max = 0;
        boolean[] visit = new boolean[N + 1];
        visit[1] = true;
        dfs(1, 0, visit);
        visit[1] = false;
        max = 0;
        visit[start] = true;
        dfs(start, 0, visit);
        visit[start] = false;
        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int vertex, int sum, boolean[] visit) {
        if (max < sum) {
            start = vertex;
            max = sum;
        }
        for (Node node : graph.get(vertex)) {
            if (visit[node.vertex]) continue;
            visit[node.vertex] = true;
            dfs(node.vertex, sum + node.cost, visit);
            visit[node.vertex] = false;
        }
    }

    static class Node {
        int vertex, cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
