import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	private static int N, M, map[][];
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 0. 입력
		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String Line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Line.charAt(j) - '0';
			}
		}

		// bfs
		int answer = bfs();
		sb.append(answer);
		System.out.append(sb);
	}

	private static int bfs() {
		// 1. 큐 생성
		Queue<Position> q = new ArrayDeque<>();
		// 2. 임의의 정점 (0,0) 방문예약
		q.add(new Position(0, 0, 1));
		boolean visit[][] = new boolean[N][M];
		visit[0][0] = true;

		while (!q.isEmpty()) {
			// 3. 큐에 있는 원소를 꺼내서 방문 처리
			Position pos = q.poll();
			// 4. 도착 여부확인
			if (pos.x == N - 1 && pos.y == M - 1) {
				return pos.count;
			}

			// 5. 상하좌우로 이동
			int direct[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
			for (int i = 0; i < 4; i++) {
				int row = pos.x + direct[i][0];
				int col = pos.y + direct[i][1];

				// 경계 안일때
				if (row >= 0 && row < N && col >= 0 && col < M) {

					// 아직 방문하지않았고, 갈 수 있는 길일때
					if (!visit[row][col] && map[row][col] == 1) {
						q.add(new Position(row, col, pos.count + 1));
						visit[row][col] = true;
					}
				}
			}
		}
		return -1; // 도착하지 못한 경우
	}

	private static class Position {
		public int x;
		public int y;
		public int count;

		public Position(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Position [x=" + x + ", y=" + y + "]";
		}
	}
}