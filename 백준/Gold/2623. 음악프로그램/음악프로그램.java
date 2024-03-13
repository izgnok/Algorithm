import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<List<Integer>> graph;
	static int[] degree;
	static List<List<Integer>> input;

	static List<Integer> result;
	static boolean impossible;

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
		degree = new int[N + 1];

		input = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			input.add(new ArrayList<>());
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size; j++) {
				int num = Integer.parseInt(st.nextToken());
				input.get(i).add(num);
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < input.get(i).size() - 1; j++) {
				int a = input.get(i).get(j);
				int b = input.get(i).get(j + 1);
				graph.get(a).add(b);
				degree[b]++;
			}
		}
		impossible = false;
		result = new ArrayList<>();
		bfs();

		if (impossible) {
			sb.append(0);
		} else {
			for (int i = 0; i < result.size(); i++) {
				sb.append(result.get(i)).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				q.add(i);
			}
		}

		for(int k=0; k<N; k++) { // 모든 정점 수 만큼 반복
			if (q.isEmpty()) { // 큐가 비어버린다면 사이클 존재
				impossible = true;
				break;
			}
			int x = q.poll();
			result.add(x);

			for (int i = 0; i < graph.get(x).size(); i++) {
				int next = graph.get(x).get(i);
				degree[next]--;
				if (degree[next] == 0)
					q.add(next);
			}
		}
	}
}