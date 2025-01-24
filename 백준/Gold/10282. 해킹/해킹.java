import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N, E, C;
    static int[] costs;
    final static int INF = 987654321;

    static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // 입력: 정점과 간선의 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            costs = new int[N + 1];
            Arrays.fill(costs, INF);
            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph.get(y).add(new Node(x, cost));
            }
            int[] answer= dijkstra();
            sb.append(answer[0]).append(" ").append(answer[1]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int[] dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(o -> o.cost));
        boolean[] visit = new boolean[N+1];

        pq.add(new Node(C, 0));
        int cost = 0;
        int count = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visit[node.vertex]) continue;
            visit[node.vertex] = true;
            cost = node.cost;
            count++;

            List<Node> children = graph.get(node.vertex);
            for(Node child: children) {
                if(visit[child.vertex]) continue;
                if(costs[child.vertex] <= node.cost + child.cost) continue;
                costs[child.vertex] = node.cost + child.cost;
                pq.add(new Node(child.vertex, costs[child.vertex]));
            }
        }
        return new int[] {count, cost};
    }


    static class Node {
        int vertex, cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "vertex=" + vertex +
                    ", cost=" + cost +
                    '}';
        }
    }
}