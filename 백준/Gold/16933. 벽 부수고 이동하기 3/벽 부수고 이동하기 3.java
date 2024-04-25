import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		result = -1;
		bfs();
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, 0, 0, 1));

		boolean visit[][][][] = new boolean[N][M][K + 1][2];
		visit[0][0][0][0] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int nextDay = dayChange(node.day);

			if (node.x == N - 1 && node.y == M - 1) {
				result = node.count;
				break;
			}

			for (int i = 0; i < 4; i++) { // 이동
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= M)
					continue;

				if (map[row][col] == 1) {
					if (node.k >= K || node.day == 1)
						continue;

					if (visit[row][col][node.k + 1][nextDay])
						continue;

					visit[row][col][node.k + 1][nextDay] = true;
					q.add(new Node(row, col, node.k + 1, nextDay, node.count + 1));
					continue;
				}

				if (visit[row][col][node.k][nextDay])
					continue;
				visit[row][col][node.k][nextDay] = true;
				q.add(new Node(row, col, node.k, nextDay, node.count + 1));
			}
			// 제자리
			if (!visit[node.x][node.y][node.k][nextDay] && node.day == 1) {
				visit[node.x][node.y][node.k][nextDay] = true;
				q.add(new Node(node.x, node.y, node.k, nextDay, node.count + 1));
			}
		}
	}

	static int dayChange(int day) {
		if (day == 0)
			return 1;
		else
			return 0;
	}

	static class Node {
		int x, y, k, count, day;

		public Node(int x, int y, int k, int day, int count) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.day = day;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", k=" + k + ", count=" + count + ", day=" + day + "]";
		}
	}
}