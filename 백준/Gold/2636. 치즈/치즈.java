import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	static int cheeze = 0;
	static int time = 0;

	static int[][] map;
	static boolean[][] air;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		air = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					cheeze++;
			}
		}

		// 초기상태에서 치즈에 닿을수 있는 공기부분 체크
		air_bfs(new Node(0, 0));

		while (cheeze > 0) { // 치즈가 1개 이상 있는 경우
			time++; // 1시간 증가

			// 공기에 닿은 치즈 제거
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1)
						cheeze_remove(new Node(i, j));
				}
			}

			if (cheeze == 0) { // 남은 치즈개수가 0이면 방금 제거된 치즈 (-1) 그대로 종료
				break;
			}

			// 방금 제거된 치즈 (-1) -> (0)으로 바꿔주고 공기 체크해줌
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == -1) {
						map[i][j] = 0;
						if (!air[i][j])
							air_bfs(new Node(i, j));
					}
				}
			}
		}

		sb.append(time).append("\n"); // 몇시간 지났는지

		int count = 0; // 마지막에 몇개 제거했는지
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == -1)
					count++;
			}
		}
		sb.append(count);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void air_bfs(Node start) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(start.x, start.y));
		air[start.x][start.y] = true;

		int[][] direct = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
		while (!q.isEmpty()) {
			Node node = q.poll();
			for (int i = 0; i < 4; i++) {
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row >= 0 && row < N && col >= 0 && col < M) {
					if (air[row][col] || map[row][col] == 1)
						continue;
					air[row][col] = true;
					q.add(new Node(row, col));
				}
			}
		}
	}

	static void cheeze_remove(Node start) {

		int[][] direct = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
		for (int i = 0; i < 4; i++) {
			int row = start.x + direct[i][0];
			int col = start.y + direct[i][1];

			if (row >= 0 && row < N && col >= 0 && col < M) {
				if (air[row][col]) { // 상하좌우에 공기가 있으면
					map[start.x][start.y] = -1; // 치즈 제거표시
					cheeze--; // 치즈 개수 감소
					break;
				}
			}
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