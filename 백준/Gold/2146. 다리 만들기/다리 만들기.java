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
	static int N;
	static int[][] map; // 지도
	static int group_count; // 섬 개수
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상 , 하 , 좌, 우
	static int min = Integer.MAX_VALUE;

	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		// 입력받기
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 1) {
					map[i][j] = -1;
				} else
					map[i][j] = 0;
			}
		}

		// 그룹으로 묶기
		group_count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == -1) {
					group_count++;
					group(new Node(i, j));
				}
			}
		}

		for (int k = 1; k <= group_count + 1; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == k) {
						bfs(k, new Node(i, j));
					}
				}
			}
		}
		visit = new boolean[group_count + 1];
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs(int num, Node node) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(node.x, node.y, 0));
		boolean visit[][] = new boolean[N][N];
		visit[node.x][node.y] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (map[cur.x][cur.y] != num && map[cur.x][cur.y] > 0) {
				if (min > cur.count -1)
					min = cur.count -1;
			}

			for (int i = 0; i < 4; i++) {
				int row = cur.x + direct[i][0];
				int col = cur.y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= N)
					continue;
				if (visit[row][col])
					continue;
				if (map[row][col] == num)
					continue;

				visit[row][col] = true;
				q.add(new Node(row, col, cur.count + 1));
			}
		}
	}

	static void group(Node start) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(start);
		map[start.x][start.y] = group_count;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;

			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];

				if (row >= 0 && row < N && col >= 0 && col < N) {
					if (map[row][col] == -1) {
						map[row][col] = group_count;
						q.add(new Node(row, col));
					}
				}
			}
		}

	}

	static class Node {
		int x, y, count;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Node(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "X: " + x + " Y: " + y;
		}
	}
}