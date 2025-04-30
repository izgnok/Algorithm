import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int INF = 987654321;
    static int N, M, K;
    static List<List<Node>> graph;
    static List<Integer> list;
    static HashMap<Integer, int[]> costs;
    static int[] totalCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            list = new ArrayList<>();
            costs = new HashMap<>();
            totalCost = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph.get(a).add(new Node(b, c));
                graph.get(b).add(new Node(a, c));
            }

            K = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                int num = Integer.parseInt(st.nextToken());
                list.add(num);
                costs.put(num, new int[N + 1]);
            }

            for (int num : list) {
                dijkstra(num);
            }

            int min = INF;
            int target = 0;
            for (int i = 1; i <= N; i++) {
                if (min <= totalCost[i]) continue;
                min = totalCost[i];
                target = i;
            }
            sb.append(target).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    static void dijkstra(int start) {
        int[] cost = costs.get(start);
        Arrays.fill(cost, INF);
        cost[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        boolean[] visit = new boolean[N + 1];

        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visit[node.vertex]) continue;
            visit[node.vertex] = true;

            List<Node> children = graph.get(node.vertex);
            for (Node child : children) {
                if (visit[child.vertex]) continue;
                if (node.cost + child.cost < cost[child.vertex]) {
                    cost[child.vertex] = node.cost + child.cost;
                    pq.add(new Node(child.vertex, node.cost + child.cost));
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            totalCost[i] += cost[i];
        }
    }


    static class Node {
        int vertex, cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "vertex: " + this.vertex + ", cost: " + this.cost;
        }
    }
}
