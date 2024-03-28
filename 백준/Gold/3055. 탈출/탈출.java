import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static char[][] map;
	static Queue<Node> water = new ArrayDeque<>();
	static Queue<Node> start = new ArrayDeque<>();
	static Node target;
	static int result = 0;
	static boolean flag = false;

	static int direct[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visit = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '*') {
					water.add(new Node(i, j));
				} else if (map[i][j] == 'S') {
					start.add(new Node(i, j, 0));
					visit[i][j] = true;
					map[i][j] = '.';
				} else if (map[i][j] == 'D') {
					target = new Node(i, j);
				}
			}
		}

		while (true) {
			flow();
			move();
			if (flag) {
				break;
			}
		}

		if (result == 0)
			sb.append("KAKTUS");
		else
			sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void flow() { // 물채우기
		int size = water.size();
		for (int k = 0; k < size; k++) {
			Node node = water.poll();

			for (int i = 0; i < 4; i++) {
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row < 0 || row >= R || col < 0 || col >= C)
					continue;

				if (map[row][col] == 'D' || map[row][col] == '*' || map[row][col] == 'X')
					continue;

				map[row][col] = '*';
				water.add(new Node(row, col));
			}
		}
	}

	static void move() {
		int size = start.size();

		boolean change = false;
		for (int k = 0; k < size; k++) {
			Node node = start.poll();
			if (node.x == target.x && node.y == target.y) {
				result = node.count;
				flag = true;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row < 0 || row >= R || col < 0 || col >= C)
					continue;

				if (map[row][col] == '*' || map[row][col] == 'X')
					continue;

				if (visit[row][col])
					continue;

				start.add(new Node(row, col, node.count + 1));
				visit[row][col] = true;
				change = true;
			}
		}

		if (!change) {
			flag = true;
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
	}
}