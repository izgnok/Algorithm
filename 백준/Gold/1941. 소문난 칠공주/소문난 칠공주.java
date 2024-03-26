import java.io.*;
import java.util.*;

public class Main {

	static char[][] map;
	static List<Node> start;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][] check;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		map = new char[5][5];
		start = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S') {
					start.add(new Node(i, j));
				}
			}
		}

		result = 0;
		check = new boolean[5][5];
		dfs(0, 0, 0, 0);
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int cnt, int row, int col) {
		if (cnt >= 4) {
			return;
		}
		if (depth == 7) {
			bfs();
			return;
		}
		if (row >= 5) {
			return;
		}

		// 선택한다
		check[row][col] = true;
		if (map[row][col] == 'Y')
			cnt++;
		if (col == 4)
			dfs(depth + 1, cnt, row + 1, 0);
		else
			dfs(depth + 1, cnt, row, col + 1);

		// 선택안한다
		check[row][col] = false;
		if (map[row][col] == 'Y')
			cnt--;
		if (col == 4)
			dfs(depth, cnt, row + 1, 0);
		else
			dfs(depth, cnt, row, col + 1);

	}

	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[5][5];
		int cnt = 0;
		boolean flag = false;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (check[i][j]) {
					q.add(new Node(i, j));
					visit[i][j] = true;
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}

		while (!q.isEmpty()) {
			Node node = q.poll();
			cnt++;

			for (int i = 0; i < 4; i++) {
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row < 0 || row >= 5 || col < 0 || col >= 5) {
					continue;
				}
				if (visit[row][col])
					continue;
				if (check[row][col]) {
					q.add(new Node(row, col));
					visit[row][col] = true;
				}
			}
		}

		if (cnt == 7) {
			result++;
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