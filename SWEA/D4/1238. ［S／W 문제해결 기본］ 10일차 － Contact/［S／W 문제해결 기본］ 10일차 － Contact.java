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

public class Solution {
	static int N, start;
	static int result;

	static List<List<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int test_case = 1; test_case <= 10; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			graph = new ArrayList<>();
			for (int i = 0; i <= 100; i++) {
				graph.add(new ArrayList<>());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				graph.get(from).add(to);
			}

			result = 0;
			bfs();
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		boolean[] visit = new boolean[101];

		q.add(new Node(start, 0));
		visit[start] = true;
		result = start;
		int max_depth = 0;

		while (!q.isEmpty()) {
			Node node = q.poll();

			if (max_depth < node.depth) {
				result = node.x;
				max_depth = node.depth;
			}
			if (max_depth == node.depth) {
				if (result < node.x)
					result = node.x;
			}

			for (int i = 0; i < graph.get(node.x).size(); i++) {
				int next = graph.get(node.x).get(i);
				if (visit[next])
					continue;
				q.add(new Node(next, node.depth + 1));
				visit[next] = true;
			}
		}
	}

	static class Node {
		int x, depth;

		Node(int x, int depth) {
			this.x = x;
			this.depth = depth;
		}
	}
}