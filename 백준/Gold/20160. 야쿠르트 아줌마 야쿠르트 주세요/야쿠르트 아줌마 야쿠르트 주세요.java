import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	static long[][] cost;
	static List<List<Node>> graph;
	static List<Integer> result;

	static long INF = Long.MAX_VALUE;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
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

		cost = new long[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(cost[i], INF);
		}
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long w = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, w));
			graph.get(b).add(new Node(a, w));
		}

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		check = new boolean[N + 1];
		int chulsu = Integer.parseInt(st.nextToken());
		Dijkstra(chulsu);
		check[chulsu] = true;
		long time = 0;
		int idx = 0;
		result = new ArrayList<>();

		int start = arr[0];

		if (start == chulsu) {
			result.add(chulsu);
		}
		while (++idx < 10) {
			int end = arr[idx];
			if (!check[start]) {
				Dijkstra(start);
				check[start] = true;
			}
			if (cost[start][end] != INF) {
				time += cost[start][end];
				if (cost[chulsu][end] <= time) {
					result.add(end);
				}
				start = end;
			}
		}

		if (result.isEmpty()) {
			sb.append("-1");
		} else {
			Collections.sort(result);
			sb.append(result.get(0));
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void Dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.w, o2.w));
		boolean visit[] = new boolean[N + 1];
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int x = node.x;
			long w = node.w;
			if (visit[x])
				continue;
			visit[x] = true;

			for (int i = 0; i < graph.get(x).size(); i++) {
				Node next = graph.get(x).get(i);
				if (visit[next.x])
					continue;

				if (cost[start][next.x] > w + next.w) {
					cost[start][next.x] = w + next.w;
					pq.add(new Node(next.x, cost[start][next.x]));
				}
			}
		}
	}

	static class Node {
		int x;
		long w;

		Node(int x, long w) {
			this.x = x;
			this.w = w;
		}
	}
}