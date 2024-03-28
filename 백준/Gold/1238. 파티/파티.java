import java.io.*;
import java.util.*;

public class Main {

	static int N, M, Party;
	static int[][] cost;
	static List<List<Node>> graph;
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Party = Integer.parseInt(st.nextToken());
		cost = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cost[i][j] = INF;
				if (i == j)
					cost[i][j] = 0;
			}
		}
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, cost));
		}

		for (int i = 1; i <= N; i++) {
			dijkstra(i);
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			if (max < cost[i][Party] + cost[Party][i])
				max = cost[i][Party] + cost[Party][i];
		}

		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		pq.add(new Node(start, 0));

		boolean visit[] = new boolean[N + 1];

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (visit[node.x])
				continue;
			visit[node.x] = true;

			for (int i = 0; i < graph.get(node.x).size(); i++) {
				Node next = graph.get(node.x).get(i);

				if (visit[next.x])
					continue;

				if (cost[start][next.x] > node.cost + next.cost) {
					cost[start][next.x] = node.cost + next.cost;
					pq.add(new Node(next.x, cost[start][next.x]));
				}
			}

		}
	}

	static class Node {
		int x, cost;

		Node(int x, int cost) {
			this.x = x;
			this.cost = cost;
		}
	}
}