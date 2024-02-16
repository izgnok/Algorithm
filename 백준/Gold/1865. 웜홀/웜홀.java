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
	static int N, M, W;

	static long[] dist;
	static Node[] edge;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			dist = new long[N + 1];

			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			edge = new Node[M * 2 + W];

			Arrays.fill(dist, 987654321);

			int idx = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				edge[idx++] = new Node(start, end, cost);
				edge[idx++] = new Node(end, start, cost);
			}
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				edge[idx++] = new Node(start, end, cost * -1);
			}

			boolean possible = true;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M * 2 + W; j++) {
					int start = edge[j].start;
					int end = edge[j].end;
					int cost = edge[j].cost;

					if (dist[end] > cost + dist[start]) {
						dist[end] = cost + dist[start];
						if (i == N - 1)
							possible = false;
					}
				}
			}

			if (possible) {
				sb.append("NO").append("\n");
			} else {
				sb.append("YES").append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		int start, end, cost;

		Node(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}

}