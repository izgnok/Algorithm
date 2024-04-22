import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;

	static Node[] start;
	static Node[] end;
	static int endState;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean visit[][][];

	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		start = new Node[3];
		end = new Node[3];
		int startIdx = 0;
		int endIdx = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = str.charAt(j);
				if (c == 'B') {
					start[startIdx++] = new Node(i, j);
					map[i][j] = 0;
				} else if (c == 'E') {
					end[endIdx++] = new Node(i, j);
					map[i][j] = 0;
				} else {
					map[i][j] = c - '0';
				}
			}
		}

		// 도착지점의 기차 상태
		if (end[1].x - end[0].x != 0) {
			endState = 1;
		}
		visit = new boolean[N][N][2];
		bfs();

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		Queue<Node> q = new ArrayDeque<>(); // 중심점을 담은 큐
		int state = 0;
		if (start[1].x - start[0].x != 0) { // 세로임
			state = 1;
		}
		q.add(new Node(start[1].x, start[1].y, 0, state)); // 좌표, 이동횟수, 상태
		visit[start[1].x][start[1].y][state] = true;

		while (!q.isEmpty()) {
			// 종료조건 생각
			Node node = q.poll();

			if (node.x == end[1].x && node.y == end[1].y && endState == node.state) {
				result = node.count;
				break;
			}

			for (int i = 0; i < 4; i++) {
				// 첫번째, 세번째 기차 확인
				if (node.state == 0) { // 가로모드
					if (!move(node.x, node.y - 1, i))
						continue;
					if (!move(node.x, node.y + 1, i))
						continue;

				} else { // 세로모드
					if (!move(node.x - 1, node.y, i))
						continue;
					if (!move(node.x + 1, node.y, i))
						continue;
				}

				// 중심점 확인
				int x = node.x + direct[i][0];
				int y = node.y + direct[i][1];
				if (x < 0 || x >= N || y < 0 || y >= N)
					continue;
				if (visit[x][y][node.state])
					continue;
				if (map[x][y] == 1)
					continue;

				visit[x][y][node.state] = true;
				q.add(new Node(x, y, node.count + 1, node.state));
			}

			// 회전
			state = changeState(node.state);
			if (visit[node.x][node.y][state])
				continue;
			if (rotate(node.x, node.y)) {
				visit[node.x][node.y][state] = true;
				q.add(new Node(node.x, node.y, node.count + 1, state));
			}
		}
	}

	static boolean move(int row, int col, int dir) {
		int x = row + direct[dir][0];
		int y = col + direct[dir][1];

		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;

		if (map[x][y] == 1)
			return false;

		return true;
	}

	static boolean rotate(int row, int col) {
		if (row - 1 < 0 || row + 1 >= N || col - 1 < 0 || col + 1 >= N)
			return false;
		if (map[row - 1][col - 1] == 1)
			return false;
		if (map[row - 1][col] == 1)
			return false;
		if (map[row][col - 1] == 1)
			return false;
		if (map[row + 1][col] == 1)
			return false;
		if (map[row][col + 1] == 1)
			return false;
		if (map[row + 1][col - 1] == 1)
			return false;
		if (map[row - 1][col + 1] == 1)
			return false;
		if (map[row + 1][col + 1] == 1)
			return false;
		return true;
	}

	static int changeState(int state) {
		if (state == 0)
			return 1;
		else
			return 0;
	}

	static class Node {
		int x, y, count, state;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int count, int state) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
			this.state = state;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", count=" + count + ", state=" + state + "]";
		}
	}
}