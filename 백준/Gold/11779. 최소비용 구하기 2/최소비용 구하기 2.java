import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	static List<List<Node>> graph = new ArrayList<>();
	static int[] cost;

	static int result;

	static int start, end;
	static int[] parent;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		cost = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, w));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		Arrays.fill(cost, Integer.MAX_VALUE);
		parent = new int[N + 1];
		dijkstra();

		sb.append(cost[end]).append("\n");

		Deque<Integer> route = new ArrayDeque<>();
		route.addLast(end);
		while (true) {
			end = parent[end];
			if (end == 0)
				break;
			route.add(end);
		}
		sb.append(route.size()).append("\n");
		while (!route.isEmpty()) {
			sb.append(route.pollLast()).append(" ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		boolean[] visit = new boolean[N + 1];
		pq.add(new Node(start, 0));
		cost[start] = 0;
		parent[start] = 0;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int v = node.v;
			int w = node.cost;
			visit[v] = true;
			if (v == end)
				return;

			for (int i = 0; i < graph.get(v).size(); i++) {
				Node next = graph.get(v).get(i);
				if (cost[next.v] > w + next.cost && !visit[next.v]) {
					cost[next.v] = w + next.cost;
					parent[next.v] = v;
					pq.add(new Node(next.v, cost[next.v]));
				}
			}
		}
	}

	static class Node {
		int v, cost;

		Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
}