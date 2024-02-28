import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] input = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] DP = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == 0 && j == 0) {
					DP[i][j] = input[0][0];
				} else if (i == 0) {
					DP[i][j] = DP[i][j - 1] + input[0][j];
				} else if (j == 0) {
					DP[i][j] = DP[i - 1][j] + input[i][0];
				} else {
					DP[i][j] = Math.max(Math.max(DP[i - 1][j], DP[i][j - 1]), DP[i - 1][j - 1]) + input[i][j];
				}
			}
		}
		sb.append(DP[N - 1][M - 1]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}