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

	static int[] depth; // 각 노드의 깊이
	static int[][] parent; // 각 노드의 1칸, 2칸, 4칸 ... 2^j 칸 위의 부모

	static StringBuilder sb = new StringBuilder();

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

		depth = new int[N + 1];
		parent = new int[N + 1][18]; // 2의 17승 == 131072

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		bfs(); // 각 노드의 깊이와 한칸 위의 부모 설정

		// 각 노드의 2^j 칸 위의 부모 설정
		for (int j = 1; j < 18; j++) {
			for (int i = 1; i <= N; i++) {
				parent[i][j] = parent[parent[i][j - 1]][j - 1];
			}
		}

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 두 노드의 깊이 맞추기
			if (depth[a] > depth[b]) {
				int k = depth[a] - depth[b];
				while (k != 0) {
					int zisu = 0;
					while (k >= Math.pow(2, zisu)) {
						zisu++;
					}
					zisu--;
					a = parent[a][zisu];
					k -= Math.pow(2, zisu);
				}

			} else if (depth[a] < depth[b]) {
				int k = depth[b] - depth[a];

				while (k != 0) {
					int zisu = 0;
					while (k >= Math.pow(2, zisu)) {
						zisu++;
					}
					zisu--;
					b = parent[b][zisu];
					k -= Math.pow(2, zisu);
				}
			}

			// 두 노드의 공통조상 구하기
			if (a != b)
				LCA(a, b);
			else
				sb.append(a).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void LCA(int a, int b) {
		for (int zisu = 17; zisu >= 0; zisu--) {
			if (parent[a][zisu] != 0 && parent[a][zisu] != parent[b][zisu]) {
				a = parent[a][zisu];
				b = parent[b][zisu];
			}
		}
		sb.append(parent[a][0]).append("\n");
	}

	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		depth[1] = 0;
		parent[1][0] = 0;
		q.add(new Node(1, depth[1]));

		boolean[] visit = new boolean[N + 1];
		visit[1] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int cost = node.cost;

			for (int i = 0; i < graph.get(x).size(); i++) {
				int child = graph.get(x).get(i);
				if (visit[child])
					continue;
				depth[child] = cost + 1;
				parent[child][0] = x;
				visit[child] = true;
				q.add(new Node(child, depth[child]));
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