import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<List<Node>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 || M == 0) break;

            graph = new ArrayList<>();
            for (int i = 0; i < N; i++) graph.add(new ArrayList<>());

            long total = 0;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                long cost = Integer.parseInt(st.nextToken());
                graph.get(x).add(new Node(y, cost));
                graph.get(y).add(new Node(x, cost));
                total += cost;
            }

            long sum = prim();
            sb.append(total - sum).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static long prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
        pq.add(new Node(0, 0));

        boolean[] visit = new boolean[N];
        int sum = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visit[node.vertex]) continue;
            visit[node.vertex] = true;
            sum += node.cost;
            if (++cnt == N) break;

            for (Node child : graph.get(node.vertex)) {
                if (visit[child.vertex]) continue;
                pq.add(new Node(child.vertex, child.cost));
            }
        }
        return sum;
    }

    static class Node {
        int vertex;
        long cost;

        Node(int vertex, long cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}