import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static boolean[] visit;
	static int[] parent;
	static List<List<Integer>> graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) { // 그래프 정점개수만큼 초기화
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			graph.get(n).add(m);
			graph.get(m).add(n);
		}
		visit = new boolean[N + 1];
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			bfs(i);
		}
		for (int i = 2; i <= N; i++) {
			sb.append(parent[i] + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static void bfs(int idx) {
		if (visit[idx]) {
			return;
		}
		visit[idx] = true;

		for (int i = 0; i < graph.get(idx).size(); i++) {
			if (parent[idx] == graph.get(idx).get(i)) continue;
			parent[graph.get(idx).get(i)] = idx;
			bfs(graph.get(idx).get(i));
		}
	}
}