import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<Node>> graph;
    static int target1, target2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int max = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, w));
            graph.get(b).add(new Node(a, w));
            max = Math.max(w, max);
        }

        st = new StringTokenizer(br.readLine());
        target1 = Integer.parseInt(st.nextToken());
        target2 = Integer.parseInt(st.nextToken());

        int start = 1;
        int end = max;
        while (start <= end) {
            int mid = (start + end) / 2;

            boolean check = bfs(mid);
            if (check) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        sb.append(start - 1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean bfs(int mid) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(target1);
        boolean[] visit = new boolean[N + 1];
        visit[target1] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Node child : graph.get(cur)) {
                if (visit[child.vertex]) continue;
                if (child.weight < mid) continue;
                if (child.vertex == target2) {
                    return true;
                }
                visit[child.vertex] = true;
                q.add(child.vertex);
            }
        }
        return false;
    }


    static class Node {
        int vertex, weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vertex=" + vertex +
                    ", weight=" + weight +
                    '}';
        }
    }
}
