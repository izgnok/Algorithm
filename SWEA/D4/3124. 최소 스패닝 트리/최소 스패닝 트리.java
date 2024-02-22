import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N, K;
	static long result, result2;

	// Kruskal
	static int parents[];
	static Edge list[];

	static class Edge implements Comparable<Edge> {
		int a, b, weight;

		Edge(int a, int b, int weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {

			return Integer.compare(this.weight, o.weight);
		}
	}

	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) {
			return false;
		}
		parents[rootB] = rootA;
		return true;
	}

	// Prim
	static List<List<Node>> graph;

	static class Node {
		int x, weight;

		Node(int x, int weight) {
			this.x = x;
			this.weight = weight;
		}
	}

	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
		boolean[] visit = new boolean[N + 1];

		pq.add(new Node(1, 0)); // 임의 정점 선택

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (visit[node.x])
				continue;
			visit[node.x] = true;
			result2 += node.weight;

			for (int i = 0; i < graph.get(node.x).size(); i++) {
				Node next = graph.get(node.x).get(i);
				if (visit[next.x])
					continue;
				pq.add(new Node(next.x, next.weight));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// Kruskal
			parents = new int[N + 1];
			list = new Edge[K];

			// Prim
			graph = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				list[i] = new Edge(a, b, weight);

				graph.get(a).add(new Node(b, weight));
				graph.get(b).add(new Node(a, weight));
			}

			result = 0;
			int cnt = 0;
			// Kruskal
			Arrays.sort(list);

			makeSet();
			for (int i = 0; i < K; i++) {
				Edge edge = list[i];
				if (union(edge.a, edge.b)) {
					result += edge.weight;
					cnt++;
				}
				if (cnt == N - 1)
					break;
			}

			// Prim
			result2 = 0;
			prim();

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
			//sb.append("#").append(test_case).append(" ").append(result2).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}