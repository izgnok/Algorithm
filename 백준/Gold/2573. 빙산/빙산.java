import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static Queue<Node> ice = new ArrayDeque<>();
	static int[][] map;

	static int direct[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean flag = false;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					ice.add(new Node(i, j));
				}
			}
		}

		do {
			melt();
			result++;
			if (ice.isEmpty()) {
				result = 0;
				break;
			}
			bfs();
		} while (!flag);
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void melt() {
		int[][] copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			copy[i] = map[i].clone();
		}
		int size = ice.size();
		for (int k = 0; k < size; k++) {
			Node node = ice.poll();
			int x = node.x;
			int y = node.y;
			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= M)
					continue;

				if (map[row][col] != 0)
					continue;
				if (copy[x][y] > 0)
					copy[x][y]--;
				else
					break;
			}
			if (copy[x][y] > 0)
				ice.add(new Node(x, y));
		}
		map = copy;
	}

	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		boolean visit[][] = new boolean[N][M];
		Node start = ice.peek();
		q.add(new Node(start.x, start.y));
		visit[start.x][start.y] = true;
		int size = ice.size();
		int cnt = 0;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			cnt++;

			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= M)
					continue;
				if (visit[row][col])
					continue;
				if (map[row][col] == 0)
					continue;

				visit[row][col] = true;
				q.add(new Node(row, col));
			}
		}
		if (cnt != size) {
			flag = true;
		}
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}