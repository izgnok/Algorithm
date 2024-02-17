import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int dist[];
	static int result;
	static int parent[];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dist = new int[100001];
		parent = new int[100001];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dijkstra(N);

		sb.append(result).append("\n");

//		Deque<Integer> stack = new ArrayDeque<>();
//		int k = M;
//		while (k != -1) {
//			stack.addLast(k);
//			k = parent[k];
//		}
//		while (!stack.isEmpty()) {
//			sb.append(stack.pollLast()).append(" ");
//		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.add(new Node(start, 0));

		boolean[] visit = new boolean[100001];
		dist[start] = 0;
		parent[start] = -1;

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int x = node.x;
			int cost = node.cost;
			visit[x] = true;
			if (x == M) {
				result = cost;
				break;
			}
			int[] direct = { 0, -1, 1 };
			for (int i = 0; i < 3; i++) {
				int next;
				if (i >= 1)
					next = x + direct[i];
				else
					next = x * 2;
				if (next >= 0 && next <= 100000) {
					if (!visit[next]) {
						if (dist[next] > cost + 1) {
							dist[next] = cost + 1;
							parent[next] = x;
							pq.add(new Node(next, dist[next]));
						}
					}
				}
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