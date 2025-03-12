import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int V, E;
    static List<List<Node>> graph = new ArrayList<>();

    static HashMap<Integer, PriorityQueue<Integer>> minCost;
    static int[] costs;
    static int INF = 987654321;

    static List<Result> results;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        minCost = new HashMap<>();
        costs = new int[V + 1];
        Arrays.fill(costs, INF);
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

            if (!minCost.containsKey(v1)) {
                minCost.put(v1, new PriorityQueue<>());
            }
            if (!minCost.containsKey(v2)) {
                minCost.put(v2, new PriorityQueue<>());
            }
            minCost.get(v1).add(cost);
            minCost.get(v2).add(cost);
        }

        results = new ArrayList<>();
        dijkstra();
        sb.append(results.size()).append("\n");
        for (Result result : results) {
            sb.append(result.x).append(" ").append(result.y).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        boolean[] visit = new boolean[V + 1];
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visit[node.vertex]) continue;
            visit[node.vertex] = true;

            if (node.pre != 0) {
                results.add(new Result(node.pre, node.vertex));
            }

            for (Node child : graph.get(node.vertex)) {
                if (visit[child.vertex]) continue;
                if (!minCost.get(child.vertex).isEmpty() && minCost.get(child.vertex).peek() > child.cost + node.cost)
                    continue;
                if (costs[child.vertex] <= child.cost + node.cost) continue;
                costs[child.vertex] = child.cost + node.cost;
                pq.add(new Node(node.vertex, child.vertex, costs[child.vertex]));
            }
        }
    }

    static class Result {
        int x, y;

        Result(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static class Node {
        int pre, vertex, cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        public Node(int pre, int vertex, int cost) {
            this.pre = pre;
            this.vertex = vertex;
            this.cost = cost;
        }
    }

}
