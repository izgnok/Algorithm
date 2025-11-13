import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static int N, M, K;
    static List<List<Node>> graph;
    static int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            int costGH = 0;

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                if ((g == a && h == b) || (g == b && h == a)) costGH = cost;
                graph.get(a).add(new Node(b, cost));
                graph.get(b).add(new Node(a, cost));
            }

            int[] target = new int[K];
            for (int i = 0; i < K; i++) {
                target[i] = Integer.parseInt(br.readLine());
            }

            int[] costS = dijkstra(s);
            int[] costG = dijkstra(g);
            int[] costH = dijkstra(h);

            List<Integer> result = new ArrayList<>();
            for (int t : target) {
                int sum1 = costS[g] + costGH + costH[t];
                int sum2 = costS[h] + costGH + costG[t];
                if (costS[t] == sum1 || costS[t] == sum2) result.add(t);
            }
            Collections.sort(result);
            for (int rs : result) sb.append(rs).append(" ");
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int[] dijkstra(int start) {

        int[] cost = new int[N + 1];
        Arrays.fill(cost, INF);
        cost[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.add(new Node(start, 0));
        boolean[] visit = new boolean[N + 1];

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visit[node.vertex]) continue;
            visit[node.vertex] = true;

            for (Node child : graph.get(node.vertex)) {
                if (visit[child.vertex]) continue;
                if (cost[child.vertex] <= node.cost + child.cost) continue;
                cost[child.vertex] = node.cost + child.cost;
                pq.add(new Node(child.vertex, cost[child.vertex]));
            }
        }
        return cost;
    }

    static class Node {
        int vertex, cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}