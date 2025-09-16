import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] cost;
    static List<List<Node>> graph;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        cost = new int[N];
        pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));

        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(br.readLine());
            pq.add(new Node(i, cost[i]));
        }

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int w = Integer.parseInt(st.nextToken());
                graph.get(j).add(new Node(i, w));
            }
        }

        prim();
        long result = 0;
        for (int i = 0; i < N; i++) result += cost[i];

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void prim() {
        boolean[] visit = new boolean[N];

        int cnt = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visit[node.v]) continue;
            visit[node.v] = true;
            if (cnt++ == N) break;

            for (Node child : graph.get(node.v)) {
                if (visit[child.v]) continue;
                if (cost[child.v] <= child.w) continue;
                cost[child.v] = child.w;
                pq.add(new Node(child.v, cost[child.v]));
            }
        }
    }

    static class Node {
        int v, w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

}