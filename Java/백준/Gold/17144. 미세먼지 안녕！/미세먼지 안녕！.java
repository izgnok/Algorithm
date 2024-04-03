import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T;
	static int[][] map;
	static Node[] cleaner = new Node[2];
	static Queue<Node> q = new ArrayDeque<>();

	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		int idx = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					q.add(new Node(i, j, map[i][j]));
				}
				if (map[i][j] == -1) {
					cleaner[idx++] = new Node(i, j, -1);
				}
			}
		}

		while (T-- > 0) {
			spread();
			cleanAir();
			next();
			if (q.isEmpty())
				break;
		}
		int result = 0;
		while (!q.isEmpty()) {
			result += q.poll().remain;
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void spread() {
		while (!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			int remain = node.remain;
			int k = remain / 5;
			if (k == 0)
				continue;

			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= M)
					continue;

				if (map[row][col] == -1)
					continue;

				map[row][col] += k;
				cnt++;
			}
			map[x][y] -= k * cnt;
		}
	}

	static void cleanAir() {
		// 위
		Node node = cleaner[0];
		int x = node.x;
		map[x][0] = 0;
		for (int i = x - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
			map[i - 1][0] = 0;
		}
		for (int j = 0; j < M - 1; j++) {
			map[0][j] = map[0][j + 1];
			map[0][j + 1] = 0;
		}
		for (int i = 0; i < x; i++) {
			map[i][M - 1] = map[i + 1][M - 1];
			map[i + 1][M - 1] = 0;
		}
		for (int j = M - 1; j > 0; j--) {
			map[x][j] = map[x][j - 1];
			map[x][j - 1] = 0;
		}
		map[x][0] = -1;

		// 아래
		node = cleaner[1];
		x = node.x;
		map[x][0] = 0;
		for (int i = x + 1; i < N - 1; i++) {
			map[i][0] = map[i + 1][0];
			map[i + 1][0] = 0;
		}
		for (int j = 0; j < M - 1; j++) {
			map[N - 1][j] = map[N - 1][j + 1];
			map[N - 1][j + 1] = 0;
		}
		for (int i = N - 1; i > x; i--) {
			map[i][M - 1] = map[i - 1][M - 1];
			map[i - 1][M - 1] = 0;
		}
		for (int j = M - 1; j > 0; j--) {
			map[x][j] = map[x][j - 1];
			map[x][j - 1] = 0;
		}
		map[x][0] = -1;
	}

	static void next() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					q.add(new Node(i, j, map[i][j]));
				}
			}
		}
	}

	static class Node {
		int x, y, remain;

		Node(int x, int y, int remain) {
			this.x = x;
			this.y = y;
			this.remain = remain;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", remain=" + remain + "]";
		}
	}
}