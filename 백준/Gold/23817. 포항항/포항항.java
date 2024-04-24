import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static Node start;
	static HashMap<Node, Integer> hash;
	static List<Node> list;
	static int[][] dist;
	static int k_size;

	static int check[];
	static boolean dfs_visit[];

	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		hash = new HashMap<>();
		list = new ArrayList<>();
		dist = new int[21][21];

		for (int i = 0; i <= 20; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S') {
					start = new Node(i, j);
					hash.put(new Node(i, j), 0);
				} else if (map[i][j] == 'K') {
					k_size++;
					hash.put(new Node(i, j), k_size);
					list.add(new Node(i, j));
				}
			}
		}

		bfs(start);
		for (int i = 0; i < list.size(); i++) {
			bfs(list.get(i));
		}

		check = new int[5];
		dfs_visit = new boolean[k_size + 1];
		result = Integer.MAX_VALUE;
		dfs(0);
		if (result == Integer.MAX_VALUE)
			result = -1;
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth) {
		if (depth == 5) {
			calc();
			return;
		}

		for (int i = 1; i <= k_size; i++) {
			if (dfs_visit[i])
				continue;
			dfs_visit[i] = true;
			check[depth] = i;
			dfs(depth + 1);
			dfs_visit[i] = false;
		}
	}

	static void calc() {

		if (dist[0][check[0]] == Integer.MAX_VALUE)
			return;
		int tmp = dist[0][check[0]];

		for (int i = 0; i < 4; i++) {
			if (dist[check[i]][check[i + 1]] == Integer.MAX_VALUE) {
				return;
			}
			tmp += dist[check[i]][check[i + 1]];
		}

		if (result > tmp)
			result = tmp;
	}

	static void bfs(Node node) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(node.x, node.y, 0));
		int idx = hash.get(node);

		boolean visit[][] = new boolean[N][M];
		visit[node.x][node.y] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (hash.get(cur) != null && hash.get(cur) != idx) {
				dist[idx][hash.get(cur)] = cur.time;
			}

			for (int i = 0; i < 4; i++) {
				int row = cur.x + direct[i][0];
				int col = cur.y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= M)
					continue;

				if (visit[row][col])
					continue;

				if (map[row][col] == 'X')
					continue;

				visit[row][col] = true;
				q.add(new Node(row, col, cur.time + 1));
			}
		}
	}

	static class Node {
		int x, y, time;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", time=" + time + "]";
		}

		@Override
		public int hashCode() {
			int result = 31;
			result = result * x;
			result = result * y;
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
			return x == other.x && y == other.y;
		}
	}
}