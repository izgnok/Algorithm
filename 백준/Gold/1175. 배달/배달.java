import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static Node start;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = str.charAt(j);
				if (c == 'S') {
					start = new Node(i, j, -1, 0, 0);
				} else if (c == 'C') {
					map[i][j] = ++idx;
				} else if (c == '#') {
					map[i][j] = -1;
				}
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
		q.add(start);

		boolean[][][][] visit = new boolean[N][M][4][4];
		visit[start.x][start.y][0][0] = true;
		visit[start.x][start.y][1][0] = true;
		visit[start.x][start.y][2][0] = true;
		visit[start.x][start.y][3][0] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.idx == 3) {
				result = node.count;
				break;
			}

			for (int i = 0; i < 4; i++) {
				if (node.dir == i)
					continue;

				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= M)
					continue;

				if (map[row][col] == -1)
					continue;

				if (visit[row][col][i][node.idx])
					continue;

				if (map[row][col] > 0) {
					int tmp = (int) 1 << (map[row][col] - 1);
					if ((node.idx & tmp) == tmp)
						continue;
					int idx = node.idx + tmp;

					if (visit[row][col][i][idx])
						continue;
					visit[row][col][i][idx] = true;
					q.add(new Node(row, col, i, idx, node.count + 1));
					continue;
				}

				visit[row][col][i][node.idx] = true;
				q.add(new Node(row, col, i, node.idx, node.count + 1));
			}
		}
	}

	static class Node {
		int x, y, dir, idx, count;

		public Node(int x, int y, int dir, int idx, int count) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.idx = idx;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", dir=" + dir + ", idx=" + idx + ", count=" + count + "]";
		}
	}
}