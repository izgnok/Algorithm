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

		int T = Integer.parseInt(st.nextToken());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] DP = new int[M + 1][N + 1];
			for (int i = 1; i <= M; i++) {
				for (int j = 0; j <= N; j++) {
					if (j == 0 || j == i)
						DP[i][j] = 1;
					else {
						DP[i][j] = DP[i - 1][j] + DP[i - 1][j - 1];
					}
				}
			}
			sb.append(DP[M][N]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}