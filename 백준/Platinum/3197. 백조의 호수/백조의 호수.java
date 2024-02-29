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
	static char[][] map; // 섬 개수
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int result = 0;

	static Queue<Node> water = new ArrayDeque<>();
	static Queue<Node> q = new ArrayDeque<>();
	static Node start[] = new Node[2];
	static boolean visit[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine().trim();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '.' || map[i][j] == 'L') {
					water.add(new Node(i, j));
				}
				if (map[i][j] == 'L') {
					start[idx++] = new Node(i, j);
				}
			}
		}

		visit = new boolean[N][M]; // 백조1의 처음 위치
		q.add(new Node(start[0].x, start[0].y));
		visit[start[0].x][start[0].y] = true;
		while (true) {
			if (move()) // 백조끼리 만나면 중단
				break;
			melt(); // 빙하 녹이기
			result++;
		}

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void melt() {
		int size = water.size();
		for (int k = 0; k < size; k++) {
			Node node = water.poll();
			int x = node.x;
			int y = node.y;

			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= M)
					continue;

				if (map[row][col] == 'X') {
					map[row][col] = '.';
					water.add(new Node(row, col));
				}
			}
		}
	}

	static boolean move() {
		Queue<Node> next = new ArrayDeque<>();

		while (!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			if (x == start[1].x && y == start[1].y) { // 백조 2를 만나면 종료
				return true;
			}

			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= M)
					continue;
				if (visit[row][col])
					continue;

				visit[row][col] = true;
				if (map[row][col] == 'X') {
					next.add(new Node(row, col));
					continue;
				}
				q.add(new Node(row, col));
			}
		}
		q = next;
		return false;
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "row: " + x + " col: " + y;
		}
	}
}