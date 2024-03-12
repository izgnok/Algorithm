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
	static int N;
	static int[] indegree;

	static List<List<Integer>> graph;
	static int last;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		indegree = new int[N];
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N -1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			graph.get(b).add(a);
			indegree[a]++;
			indegree[b]++;
		}
		remove();
		
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if (indegree[i] >= 1) {
				result.add(i);
			}
		}
		Collections.sort(result);
		for(int i=0; i< result.size(); i++) {
			sb.append(result.get(i)).append(" ");
		}
		if(result.size() == 0) sb.append(last);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void remove() {
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			if (indegree[i] == 1) {
				q.add(i);
			}
		}

		while (true) {
			int size = q.size();
			if (size <= 2) {
				break;
			}
			for (int k = 0; k < size; k++) {
				int x = q.poll();
				indegree[x]--;
				for (int i = 0; i < graph.get(x).size(); i++) {
					int next = graph.get(x).get(i);
					indegree[next]--;
					if (indegree[next] == 1) {
						q.add(next);
						last = next;
					}
				}
			}
		}
	}
}