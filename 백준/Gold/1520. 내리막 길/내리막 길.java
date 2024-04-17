import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int direct[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static int DP[][];
	static boolean visit[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DP = new int[N][M];
		visit = new boolean[N][M];
		dfs(0, 0);
		sb.append(DP[0][0]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int dfs(int row, int col) {
		for (int i = 0; i < 4; i++) {
			int nextRow = row + direct[i][0];
			int nextCol = col + direct[i][1];

			if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) // 범위 벗어남
				continue;

			if (map[nextRow][nextCol] >= map[row][col]) // 다음칸이 더 큼
				continue;

			if (visit[nextRow][nextCol]) { // 이미 방문한 경우
				DP[row][col] += DP[nextRow][nextCol];
				continue;
			}
			
			if(nextRow == N -1 && nextCol == M - 1) { // 끝에 도달함
				DP[row][col] +=1;
				continue;
			}
			
			visit[nextRow][nextCol] = true;
			DP[row][col] += dfs(nextRow, nextCol);

		}
		return DP[row][col];
	}
}