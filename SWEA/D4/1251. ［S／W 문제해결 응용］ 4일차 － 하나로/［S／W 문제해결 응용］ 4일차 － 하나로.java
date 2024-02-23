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
	static int N;
	static double E;
	static double result;

	static long input_x[];
	static long input_y[];
	static Node input[];
	static List<List<Node>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			graph = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				graph.add(new ArrayList<>());
			}

			input = new Node[N];
			input_x = new long[N];
			input_y = new long[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				input_x[i] = Long.parseLong(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				input_y[i] = Long.parseLong(st.nextToken());
			}

			for (int i = 0; i < N; i++) {
				input[i] = new Node(i, input_x[i], input_y[i]);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					graph.get(i).add(input[j]);
				}
			}

			st = new StringTokenizer(br.readLine());
			E = Double.parseDouble(st.nextToken());

			result = 0.0;
			prim();
			long result2 = (Long) Math.round(result); // 반올림 후 정수로 바꿔주기
			sb.append("#").append(test_case).append(" ").append(result2).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		Node start = input[0];
		pq.add(new Node(start.num, start.x, start.y, 0));
		boolean visit[] = new boolean[N];
		int cnt = 0;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (visit[node.num])
				continue;
			result += E * Math.pow(node.dist, 2);
			visit[node.num] = true;

			if (++cnt == N)
				break;

			for (int i = 0; i < graph.get(node.num).size(); i++) {
				Node next = graph.get(node.num).get(i);
				if (visit[next.num])
					continue;
				double dist = Math.sqrt(Math.pow((node.x - next.x), 2) + Math.pow((node.y - next.y), 2));
				pq.add(new Node(next.num, next.x, next.y, dist));
			}
		}
	}

	static class Node implements Comparable<Node> {
		long x, y;
		int num;
		double dist;

		Node(int num, long x, long y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}

		Node(int num, long x, long y, double dist) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.dist, o.dist);
		}
	}
}