import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, start;
	static int[] cost;
	static List<List<Node>> graph;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		cost = new int[N+1];
		Arrays.fill(cost, Integer.MAX_VALUE);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, w));
		}

		cost[start] = 0;
		visit = new boolean[N+1];
		dijkstra();

		for (int i = 1; i <= N; i++) {
			if (cost[i] == Integer.MAX_VALUE)
				sb.append("INF\n");
			else
				sb.append(cost[i]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.add(new Node(start, 0));
		//visit[start] = true;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int x = node.x;
			int w = node.cost;

			for (int i = 0; i < graph.get(x).size(); i++) {
				Node tmp = graph.get(x).get(i);
				//if(visit[tmp.x]) continue;
				
				if (cost[tmp.x] > w + tmp.cost) {
					cost[tmp.x] = w + tmp.cost;
					pq.add(new Node(tmp.x, cost[tmp.x]));
					//visit[tmp.x] = true;
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