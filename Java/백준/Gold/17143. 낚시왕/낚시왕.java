import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.num, o1.num));

	static int[][] map;
	static int[][] direct = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static boolean[] death;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		death = new boolean[10001];
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			if (d == 1 || d == 2) {
				s %= ((N - 1) * 2);
			} else if (d == 3 || d == 4) {
				s %= ((M - 1) * 2);
			}

			map[x][y] = num;
			pq.add(new Node(x, y, s, d, num));
		}

		for (int i = 1; i <= M; i++) {
			fishing(i);
			move();
		}

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void move() {
		PriorityQueue<Node> next = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.num, o1.num));
		boolean[][] visit = new boolean[N + 1][M + 1];
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int num = node.num;
			if (death[num]) // 움직이기전에 잡힌 경우
				continue;
			int x = node.x;
			int y = node.y;
			int s = node.s;
			int d = node.d;

			int row = x;
			int col = y;
			while (s-- > 0) {
				row += direct[d][0];
				col += direct[d][1];

				if (row < 1 || row > N || col < 1 || col > M) {
					d = change(d);
					row += direct[d][0] * 2;
					col += direct[d][1] * 2;
				}
			}

			if (!visit[x][y])
				map[x][y] = 0;
			if (visit[row][col]) {
				death[num] = true;
				continue;
			}
			next.add(new Node(row, col, node.s, d, num));
			visit[row][col] = true;
			map[row][col] = num;
		}
		pq = next;
	}

	static int change(int d) {
		if (d == 1)
			return 2;
		if (d == 2)
			return 1;
		if (d == 3)
			return 4;
		if (d == 4)
			return 3;
		return 0;
	}

	static void fishing(int idx) {
		for (int i = 1; i <= N; i++) {
			if (map[i][idx] > 0) {
				result += map[i][idx];
				death[map[i][idx]] = true;
				map[i][idx] = 0;
				break;
			}
		}
	}

	static class Node {
		int x, y, s, d, num;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int s, int d, int num) {
			super();
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", 번호=" + num + ", 속력=" + s + ", 방향=" + d + "]";
		}
	}
}