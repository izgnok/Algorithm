import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int result = 0;

	static List<List<Integer>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		bfs();
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean visit[] = new boolean[N + 1];
		q.add(1);
		visit[1] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();
			if (cur != 1)
				result++;

			for (int i = 0; i < graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i);
				if (visit[next])
					continue;
				q.add(next);
				visit[next] = true;
			}
		}

	}
}