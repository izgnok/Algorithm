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
	static int N, K;
	static int[][] map;
	static HashMap<Node, Deque<Node>> hash;
	static Node[] list;
	static int[][] direct = { { 0, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 }, }; // 우, 좌, 상, 하
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		hash = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				hash.put(new Node(i, j), new ArrayDeque<>());
			}
		}

		list = new Node[K + 1];
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Node node = new Node(x, y, d, i);
			hash.get(new Node(x, y)).addLast(node);
			list[i] = node;
		}

		int result = 0;
		while (++result <= 1000) {
			game();
			if (flag) {
				break;
			}
		}
		if (result == 1001) {
			result = -1;
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void game() {
		for (int i = 1; i <= K; i++) {
			if (flag)
				break;
			Node node = list[i];
			int x = node.x;
			int y = node.y;
			if (i != hash.get(new Node(x, y)).peekFirst().num)
				continue;
			int d = node.d;
			int row = x + direct[d][0];
			int col = y + direct[d][1];

			// 파랑색
			if (row < 1 || row > N || col < 1 || col > N || map[row][col] == 2) {
				d = change(d);
				row += direct[d][0] * 2;
				col += direct[d][1] * 2;

				if (row < 1 || row > N || col < 1 || col > N || map[row][col] == 2) {
					list[i].d = d;
					continue;
				} else if (map[row][col] == 1) {
					red(row, col, x, y, i, d);
				} else {
					blank(row, col, x, y, i, d);
				}
			}
			// 빨간색
			else if (map[row][col] == 1) {
				red(row, col, x, y, i, d);
			}
			// 빈칸
			else {
				blank(row, col, x, y, i, d);
			}
		}
	}

	static void red(int row, int col, int x, int y, int num, int d) {
		Deque<Node> dq = hash.get(new Node(x, y));
		Deque<Node> dq2 = hash.get(new Node(row, col));

		Deque<Node> stack = new ArrayDeque<>();
		while (true) {
			Node tmp = dq.pollLast();
			Node node = new Node(row, col, tmp.d, tmp.num);
			if (tmp.num == num)
				node.d = d;
			list[tmp.num] = node;
			stack.addLast(node);
			if (tmp.num == num) {
				break;
			}
		}
		while (!stack.isEmpty()) {
			dq2.addLast(stack.pollFirst());
		}
		if (dq2.size() >= 4) {
			flag = true;
		}
	}

	static void blank(int row, int col, int x, int y, int num, int d) {
		Deque<Node> dq = hash.get(new Node(x, y));
		Deque<Node> dq2 = hash.get(new Node(row, col));

		Deque<Node> stack = new ArrayDeque<>();
		while (true) {
			Node tmp = dq.pollLast();
			Node node = new Node(row, col, tmp.d, tmp.num);
			if (tmp.num == num)
				node.d = d;
			list[tmp.num] = node;
			stack.addLast(node);
			if (tmp.num == num) {
				break;
			}
		}
		while (!stack.isEmpty()) {
			dq2.addLast(stack.pollLast());
		}
		if (dq2.size() >= 4) {
			flag = true;
		}
	}

	static int change(int d) {
		if (d == 1)
			return 2;
		if (d == 2)
			return 1;
		if (d == 3)
			return 4;
		if (d == 4)
			return 3;
		return 0;
	}

	static class Node {
		int x, y, d, num;

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", d=" + d + ", num=" + num + "]";
		}

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}

		public Node(int x, int y, int d, int num) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.num = num;
		}

		@Override
		public int hashCode() {
			int result = 17;
			result = 31 * result * this.x;
			result = 31 * result * this.y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			return this.x == other.x && this.y == other.y;
		}

	}
}