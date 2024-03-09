import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int[][] result;

	static List<Node> empty;
	static List<Node> list;
	static Group[][] group;
	static int group_count;
	static int direct[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		list = new ArrayList<>();
		empty = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
				if (map[i][j] == 1) {
					list.add(new Node(i, j));
				} else {
					empty.add(new Node(i, j));
				}
			}
		}

		visit = new boolean[N][M];
		group = new Group[N][M];
		group_count = 0;
		for (int i = 0; i < empty.size(); i++) {
			Node node = empty.get(i);
			if (visit[node.x][node.y])
				continue;
			bfs(node);
			group_count++;
		}

		result = new int[N][M];
		for (int i = 0; i < list.size(); i++) {
			boolean group_visit[] = new boolean[group_count];
			Node node = list.get(i);

			int sum = 1;
			for (int k = 0; k < 4; k++) {
				int row = node.x + direct[k][0];
				int col = node.y + direct[k][1];

				if (row < 0 || row >= N || col < 0 || col >= M) {
					continue;
				}
				if (map[row][col] == 1)
					continue;
				int group_num = group[row][col].num;
				if (group_visit[group_num])
					continue;
				group_visit[group_num] = true;
				sum += group[row][col].count;
			}
			result[node.x][node.y] = sum % 10;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(result[i][j]);
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs(Node start) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(start);
		visit[start.x][start.y] = true;

		int count = 0;
		Queue<Node> group_q = new ArrayDeque<>();
		while (!q.isEmpty()) {
			Node node = q.poll();
			group_q.add(node);
			count++;

			for (int i = 0; i < 4; i++) {
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= M)
					continue;

				if (visit[row][col])
					continue;

				if (map[row][col] == 1)
					continue;

				visit[row][col] = true;
				q.add(new Node(row, col));
			}
		}

		while (!group_q.isEmpty()) {
			Node node = group_q.poll();
			group[node.x][node.y] = new Group(count, group_count);
		}
	}

	static class Group {
		int count, num;

		Group(int count, int num) {
			this.count = count;
			this.num = num;
		}

		@Override
		public String toString() {
			return "그룹번호: " + num + " 개수: " + count;
		}
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