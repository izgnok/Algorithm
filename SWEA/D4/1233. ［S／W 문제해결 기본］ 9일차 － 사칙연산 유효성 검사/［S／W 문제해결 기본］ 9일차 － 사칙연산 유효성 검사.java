import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static char[] input;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= 10; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			input = new char[N + 1];
			graph = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				String str = st.nextToken();
				input[idx] = str.charAt(0);
				try {
					graph.get(idx).add(Integer.parseInt(st.nextToken()));
					graph.get(idx).add(Integer.parseInt(st.nextToken()));
				} catch (Exception e) {
					continue;
				}
			}
			
			int result = 1;
			for(int i=1; i<= N; i++) {
				if (input[i] == '+' || input[i] == '-' || input[i] == '*' || input[i] == '/') {
					if(graph.get(i).size() < 2) {
						result = 0;
						break;
					}
				}
				else {
					if(graph.get(i).size() > 0) {
						result = 0;
						break;
					}
				}
			}

			//int result = dfs(1);
			sb.append("#" + test_case + " ");
			sb.append(String.valueOf(result) + '\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int dfs(int idx) {
		if (input[idx] == '+' || input[idx] == '-' || input[idx] == '*' || input[idx] == '/') {
			if (graph.get(idx).size() < 2)
				return 0;
			int k = graph.get(idx).get(0);
			int cur = dfs(k);
			k = graph.get(idx).get(0);
			int cur2 = dfs(k);
			if (cur == 0 || cur2 == 0)
				return 0;
			return 1;
		}

		if (graph.get(idx).size() > 0)
			return 0;
		else
			return 1;
	}
}