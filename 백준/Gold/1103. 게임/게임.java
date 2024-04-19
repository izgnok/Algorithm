import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1103, 게임 .. 무한으로 도는 걸 판별하는 방법을 떠올려보자
public class Main {
	static int N, M;
	static int[][] map;
	static int[][] DP;
	static boolean[][] visit;
	static boolean flag;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				if (str.charAt(j) == 'H') {
					map[i][j] = -1;
				} else {
					map[i][j] = str.charAt(j) - '0';
				}
			}
		}
		DP = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(DP[i], -1);
		}
		dfs(0, 0);
		if (flag)
			sb.append("-1");
		else
			sb.append(DP[0][0]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int dfs(int row, int col) {
		if (row < 0 || row >= N || col < 0 || col >= M) { // 범위 밖
			return 0;
		}
		if (map[row][col] == -1) { // 구멍에 빠짐
			return 0;
		}
		if (visit[row][col]) { // 무한으로 도는 것을 판별
			flag = true;
			return -1;
		}
		if (DP[row][col] != -1) { // 이미 해당 칸에 방문함
			return DP[row][col];
		}

		visit[row][col] = true;
		DP[row][col] = 0;
		for (int i = 0; i < 4; i++) {
			int x = row + (direct[i][0] * map[row][col]);
			int y = col + (direct[i][1] * map[row][col]);

			DP[row][col] = Math.max(DP[row][col], 1 + dfs(x, y));
			if (flag)
				return -1;
		}
		visit[row][col] = false;
		return DP[row][col];
	}
}