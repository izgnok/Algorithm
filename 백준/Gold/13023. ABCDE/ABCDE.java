import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static List<List<Integer>> graph;
	static int N, M;

	static boolean[] visit;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		for (int i = 0; i < N; i++) {
			visit = new boolean[N];
			visit[i] = true;
			if (dfs(0, i)) {
				result = 1;
				break;
			}
			visit[i] = false;
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static boolean dfs(int depth, int idx) {
		if (depth == 4) {
			return true;
		}

		for (int i = 0; i < graph.get(idx).size(); i++) {
			int next = graph.get(idx).get(i);
			if (visit[next])
				continue;
			visit[next] = true;
			if (dfs(depth + 1, next)) {
				return true;
			}
			visit[next] = false;

		}

		return false;
	}
}