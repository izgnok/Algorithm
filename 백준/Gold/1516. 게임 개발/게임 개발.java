import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<List<Integer>> graph;
	static StringBuilder sb = new StringBuilder();

	// bfs
	static int[] in_degree;
	static int[] cost;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		in_degree = new int[N + 1];
		cost = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			cost[i] = w;

			while (true) {
				int a = Integer.parseInt(st.nextToken());
				if (a != -1) {
					graph.get(a).add(i);
					in_degree[i]++;
				}
				else break;
			}
		}

		bfs();
		for (int i = 1; i <= N; i++) {
			sb.append(cost[i]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> cost[o1] - cost[o2]);
		for (int i = 1; i <= N; i++) {
			if (in_degree[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int x = q.poll();

			for (int i = 0; i < graph.get(x).size(); i++) {
				int next = graph.get(x).get(i);
				in_degree[next]--;
				if (in_degree[next] == 0) {
					cost[next] = cost[next] + cost[x];
					q.add(next);
				}
			}
		}
	}

}