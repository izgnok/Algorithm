import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] people;
	static List<ArrayList<Integer>> graph;
	static boolean[] group;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		people = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken());

			while (j-- > 0) {
				int k = Integer.parseInt(st.nextToken());
				graph.get(i).add(k);
			}
		}

		for (int i = 1; i <= N; i++) {
			group = new boolean[N + 1];
			dfs(i);
		}
		if(result == Integer.MAX_VALUE) result = -1;
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int idx) {
		group[idx] = true;
		bfs(); // 인구 수 차이 구하기

		for (int i = idx + 1; i <= N; i++) {
			group[i] = true;
			dfs(i);
			group[i] = false;
		}

	}

	static void bfs() {
		boolean[] visited = new boolean[N + 1];

		// 그룹1
		int sum1 = 0;
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (group[i]) {
				q.add(i);
				visited[i] = true;
				sum1 += people[i];
				break;
			}
		}
		while (!q.isEmpty()) {
			int idx = q.poll();
			for (int i = 0; i < graph.get(idx).size(); i++) {
				int k = graph.get(idx).get(i);
				if (!visited[k] && group[k]) {
					q.add(k);
					visited[k] = true;
					sum1 += people[k];
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (group[i] && !visited[i])
				return; // 인접한 구역이 아님
		}

		// 그룹2
		int sum2 = 0;
		for (int i = 1; i <= N; i++) {
			if (!group[i]) {
				q.add(i);
				visited[i] = true;
				sum2 += people[i];
				break;
			}
		}
		while (!q.isEmpty()) {
			int idx = q.poll();
			for (int i = 0; i < graph.get(idx).size(); i++) {
				int k = graph.get(idx).get(i);
				if (!visited[k] && !group[k]) {
					q.add(k);
					visited[k] = true;
					sum2 += people[k];
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (!group[i] && !visited[i])
				return; // 인접한 구역이 아님
		}

		int sum = Math.abs(sum2 - sum1);
		if (result > sum)
			result = sum;
	}
}