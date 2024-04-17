import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[][] DP;
	static boolean[][] visit;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		DP = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j])
					continue;
				visit[i][j] = true;
				dfs(i, j);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (result < DP[i][j])
					result = DP[i][j];
			}
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int dfs(int row, int col) {
		int tmp = DP[row][col];
		for (int i = 0; i < 4; i++) {
			int x = row + direct[i][0];
			int y = col + direct[i][1];

			if (x < 0 || x >= N || y < 0 || y >= N)
				continue;
			
			if (map[x][y] <= map[row][col]) {
				DP[row][col] = Math.max(DP[row][col], 1);
				continue;
			}

			if (visit[x][y]) {
				DP[row][col] = Math.max(DP[row][col], 1 + DP[x][y]);
				continue;
			}


			visit[x][y] = true;
			int k = dfs(x, y);
			DP[row][col] = Math.max(DP[row][col], k);
		}
		return DP[row][col] + 1;
	}
}