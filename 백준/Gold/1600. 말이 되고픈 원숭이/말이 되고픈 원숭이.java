import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K, N, M;
	static int[][] map;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][] horse = { { -2, 1 }, { -2, -1 }, { 2, 1 }, { 2, -1 }, { 1, 2 }, { -1, 2 }, { 1, -2 }, { -1, -2 } };
	static boolean[][][] visit;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visit = new boolean[N][M][K + 1];
		result = Integer.MAX_VALUE;
		bfs();

		if (result == Integer.MAX_VALUE)
			result = -1;

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		visit[0][0][0] = true;
		q.add(new Node(0, 0, 0, 0));

		while (!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			int k = node.k;
			int count = node.count;

			if (x == N - 1 && y == M - 1) {
				if (result > count) {
					result = count;
				}
				break;
			}

			if (k < K) {
				for (int i = 0; i < 8; i++) {
					int row = x + horse[i][0];
					int col = y + horse[i][1];
					if (row >= 0 && row < N && col >= 0 && col < M) {
						if (map[row][col] == 1)
							continue;
						if (visit[row][col][k + 1])
							continue;
						visit[row][col][k + 1] = true;
						q.add(new Node(row, col, k + 1, count + 1));
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];
				if (row >= 0 && row < N && col >= 0 && col < M) {
					if (map[row][col] == 1)
						continue;
					if (visit[row][col][k])
						continue;
					visit[row][col][k] = true;
					q.add(new Node(row, col, k, count + 1));
				}
			}
		}
	}

	static class Node {
		int x, y, k, count;

		Node(int x, int y, int k, int count) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.count = count;
		}
	}
}