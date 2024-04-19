import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, A, B, C;
	static long[][][][] DP;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		DP = new long[N + 1][A + 1][B + 1][C + 1];

		for (int i = 0; i <= N; i++) {
			for (int a = 0; a <= A; a++) {
				for (int b = 0; b <= B; b++) {
					for (int c = 0; c <= C; c++) {
						if (i == 0) {
							if (a == 0 && b == 0 && c == 0) {
								DP[0][0][0][0] = 1;
							} else
								DP[i][a][b][c] = 0;
						} else {
							if (a == 0 && b == 0 && c == 0) {
								DP[i][a][b][c] = 0;
							} else if (b == 0 && c == 0) {
								DP[i][a][b][c] = DP[i - 1][a - 1][b][c];
							} else if (a == 0 && c == 0) {
								DP[i][a][b][c] = DP[i - 1][a][b - 1][c];
							} else if (a == 0 && b == 0) {
								DP[i][a][b][c] = DP[i - 1][a][b][c - 1];
							} else if (a == 0) {
								DP[i][a][b][c] = DP[i - 1][a][b - 1][c] + DP[i - 1][a][b][c - 1]
										+ DP[i - 1][a][b - 1][c - 1];
							} else if (b == 0) {
								DP[i][a][b][c] = DP[i - 1][a - 1][b][c] + DP[i - 1][a][b][c - 1]
										+ DP[i - 1][a - 1][b][c - 1];
							} else if (c == 0) {
								DP[i][a][b][c] = DP[i - 1][a - 1][b][c] + DP[i - 1][a][b - 1][c]
										+ DP[i - 1][a - 1][b - 1][c];
							} else {
								DP[i][a][b][c] = DP[i - 1][a - 1][b][c] + DP[i - 1][a][b - 1][c]
										+ DP[i - 1][a][b][c - 1] + DP[i - 1][a - 1][b - 1][c]
										+ DP[i - 1][a - 1][b][c - 1] + DP[i - 1][a][b - 1][c - 1]
										+ DP[i - 1][a - 1][b - 1][c - 1];
							}
							DP[i][a][b][c] %= 1000000007;
						}
					}
				}
			}
		}
		sb.append(DP[N][A][B][C]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}