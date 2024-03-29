import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map; // 지도
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상 , 하 , 좌, 우
	static Queue<Node> virus = new ArrayDeque<>();
	static List<Node> list = new ArrayList<>();
	static boolean check[];
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					list.add(new Node(i, j, 0));
				}
			}
		}

		check = new boolean[list.size()];
		result = Integer.MAX_VALUE;
		dfs(0, 0);
		if (result == Integer.MAX_VALUE)
			result = -1;
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int idx) {
		if (depth == M) {
			bfs();
		}

		for (int i = idx; i < list.size(); i++) {
			if (check[i])
				continue;
			check[i] = true;
			dfs(depth + 1, i + 1);
			check[i] = false;
		}
	}

	static void bfs() {
		boolean visit[][] = new boolean[N][N];
		int copy[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(copy[i], -1);
		}
		for (int i = 0; i < list.size(); i++) {
			Node node = list.get(i);
			copy[node.x][node.y] = 0;
			if (check[i]) {
				virus.add(node);
				visit[node.x][node.y] = true;
			}
		}

		while (!virus.isEmpty()) {
			Node node = virus.poll();
			int x = node.x;
			int y = node.y;
			int time = node.time;

			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= N)
					continue;

				if (visit[row][col])
					continue;

				if (map[row][col] == 1)
					continue;

				if (map[row][col] == 2) {
					visit[row][col] = true;
					virus.add(new Node(row, col, time + 1));
				}

				if (copy[row][col] == -1 || copy[row][col] > time + 1) {
					copy[row][col] = time + 1;
					virus.add(new Node(row, col, time + 1));
				}
			}
		}

		int max = 0;
		boolean possible = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (copy[i][j] == -1 && map[i][j] != 1) {
					possible = false;
					break;
				}

				if (map[i][j] == 2)
					continue;

				if (copy[i][j] > max) {
					max = copy[i][j];
				}
			}
			if (!possible) {
				max = Integer.MAX_VALUE;
				break;
			}
		}
		if (result > max)
			result = max;
	}

	static class Node {
		int x, y, time;

		Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", time=" + time + "]";
		}

	}
}