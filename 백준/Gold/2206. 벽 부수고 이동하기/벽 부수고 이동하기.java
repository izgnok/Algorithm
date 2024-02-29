import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int result;
	static int[][] map;

	static int[][] direct = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine().trim();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		bfs();
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, 1, 0));

		boolean visit[][][] = new boolean[N][M][2];
		visit[0][0][0] = true;

		boolean possible = false;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			int count = node.count;
			int k = node.k;
			if (x == N - 1 && y == M - 1) {
				result = count;
				possible = true;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];

				if (row >= 0 && row < N && col >= 0 && col < M) {
					if (map[row][col] == 1) {
						if (k >= 1 || visit[row][col][k + 1])
							continue;
						q.add(new Node(row, col, count + 1, k + 1));
						visit[row][col][k + 1] = true;
					} else {
						if (visit[row][col][k])
							continue;
						q.add(new Node(row, col, count + 1, k));
						visit[row][col][k] = true;
					}
				}
			}
		}
		if (!possible)
			result = -1;
	}

	static class Node {
		int x, y, count, k;

		Node(int x, int y, int count, int k) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.k = k;
		}
	}

}