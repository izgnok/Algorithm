import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, }; // 상 하 좌 우

	static Node taxi;
	static Node[] target;
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					map[i][j] = -1;
			}
		}

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		taxi = new Node(x, y);
		target = new Node[M + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			map[x][y] = i;

			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			target[i] = new Node(x, y);
		}

		while (K > 0 && M > 0) {
			find();
			if (!flag)
				break;
			drive();
			if (!flag)
				break;
		}
		if (K >= 0 && M == 0)
			sb.append(K);
		else
			sb.append("-1");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void find() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(taxi);

		boolean visit[][] = new boolean[N + 1][N + 1];
		visit[taxi.x][taxi.y] = true;

		int min = Integer.MAX_VALUE;
		flag = false;
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (map[node.x][node.y] > 0) {
				if (min > node.cost) {
					min = node.cost;
					taxi = new Node(node.x, node.y, node.cost, map[node.x][node.y]);
					flag = true;
				} else if (min == node.cost) {
					if (node.x < taxi.x) {
						taxi = new Node(node.x, node.y, node.cost, map[node.x][node.y]);
					} else if (node.x == taxi.x && node.y < taxi.y) {
						taxi = new Node(node.x, node.y, node.cost, map[node.x][node.y]);
					}
				} else {
					return;
				}
			}

			for (int i = 0; i < 4; i++) {
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row < 1 || row > N || col < 1 || col > N)
					continue;
				if (map[row][col] == -1)
					continue;
				if (visit[row][col])
					continue;

				visit[row][col] = true;
				q.add(new Node(row, col, node.cost + 1, 0));
			}
		}
	}

	static void drive() {
		if (K < 0)
			return;
		Queue<Node> q = new ArrayDeque<>();
		q.add(taxi);

		boolean visit[][] = new boolean[N + 1][N + 1];
		visit[taxi.x][taxi.y] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			for (int i = 0; i < 4; i++) {
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row < 1 || row > N || col < 1 || col > N)
					continue;
				if (map[row][col] == -1)
					continue;
				if (visit[row][col])
					continue;

				if (target[taxi.num].x == row && target[taxi.num].y == col) {
					K -= node.cost + 1;
					if (K >= 0) {
						K += (node.cost + 1 - taxi.cost) * 2;
						map[taxi.x][taxi.y] = 0;
						taxi = new Node(row, col);
						M--;
					}
					return;
				}
				visit[row][col] = true;
				q.add(new Node(row, col, node.cost + 1, 0));
			}
		}
		flag = false;
	}

	static class Node {
		int x, y, cost, num;

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", cost=" + cost + ", num=" + num + "]";
		}

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int cost, int num) {
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.num = num;
		}
	}
}