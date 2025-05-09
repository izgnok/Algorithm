import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N;

    static List<List<Node>> graph;
    static int max;
    static int start;

    static int count = 0;

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

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            while (true) {
                int b = Integer.parseInt(st.nextToken());
                if (b == -1) break;
                int c = Integer.parseInt(st.nextToken());
                graph.get(a).add(new Node(b, c));
            }
        }

        // DFS 1
//        max = 0;
//        boolean[] visit1 = new boolean[N + 1];
//        visit1[1] = true;
//        dfs(1, 0, visit1);
//
//        // DFS 2
//        boolean[] visit2 = new boolean[N + 1];
//        max = 0;
//        visit2[start] = true;
//        dfs(start, 0, visit2);

        max = 0;
        boolean[] visit = new boolean[N + 1];
        visit[1] = true;
        dfs(1, 0, visit);
        visit[1] = false;
        max = 0;
        visit[start] = true;
        dfs(start, 0, visit);
        visit[start] = false;
//        System.out.println(count);
        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int vertex, int sum, boolean[] visit) {
        count++;
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
