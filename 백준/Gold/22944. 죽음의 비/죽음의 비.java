import java.io.*;
import java.util.*;

public class Main {

	static int N, H, D;
	static char[][] map;

	static Node start, end;
	static List<Node> umb;
	static boolean[] check;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new char[N][N];
		umb = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S') {
					start = new Node(i, j);
				} else if (map[i][j] == 'E') {
					end = new Node(i, j);
				} else if (map[i][j] == 'U') {
					umb.add(new Node(i, j));
				}
			}
		}

		result = Integer.MAX_VALUE;
		check = new boolean[umb.size()];
		dfs(0, start.x, start.y, H, 0, 0);
		if (result == Integer.MAX_VALUE)
			result = -1;

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int row, int col, int life, int shield, int sum) {
		int dist = Math.abs(row - end.x) + Math.abs(col - end.y);
		if (dist <= life + shield) {
			if (result > sum + dist)
				result = sum + dist;
			return;
		}

		if (depth == umb.size()) {
			return;
		}

		for (int i = 0; i < umb.size(); i++) {
			if (check[i])
				continue;

			Node node = umb.get(i);
			int d = Math.abs(row - node.x) +  Math.abs(col - node.y);
			if (d <= life + shield) {
				check[i] = true;
				shield -= (d - 1);
				if (shield < 0)
					life += shield;
				dfs(depth + 1, node.x, node.y, life, D - 1, sum + d);
				check[i] = false;
				if (shield < 0)
					life -= shield;
				shield += (d - 1);
			}
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
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}
}