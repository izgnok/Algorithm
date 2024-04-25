import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static Node start;
	static int size;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0)
				break;

			map = new int[N][M];
			size = 0;
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					char c = str.charAt(j);
					if (c == 'o') {
						start = new Node(i, j, 0, 0);
					} else if (c == '*') {
						map[i][j] = ++size;
					} else if (c == 'x') {
						map[i][j] = -1;
					}
				}
			}
			result = -1;
			bfs();
			sb.append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(start);

		boolean[][][] visit = new boolean[N][M][(int) Math.pow(2, size)];
		visit[start.x][start.y][start.idx] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.idx == (int) Math.pow(2, size) - 1) {
				result = node.dist;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= M)
					continue;

				if (map[row][col] == -1)
					continue;

				if (visit[row][col][node.idx])
					continue;

				int tmp = (int) 1 << (map[row][col] - 1);
				if (map[row][col] > 0 && (node.idx & tmp) != tmp) {

					int idx = node.idx + tmp;

					if (visit[row][col][idx])
						continue;
					visit[row][col][idx] = true;
					q.add(new Node(row, col, idx, node.dist + 1));
					continue;
				}

				visit[row][col][node.idx] = true;
				q.add(new Node(row, col, node.idx, node.dist + 1));
			}
		}
	}

	static class Node {
		int x, y, idx, dist;

		Node(int x, int y, int idx, int dist) {
			this.x = x;
			this.y = y;
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", idx=" + idx + ", dist=" + dist + "]";
		}

	}
}