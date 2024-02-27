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

		int[] DP = new int[1000001];

		DP[1] = 0;
		DP[2] = 1;
		DP[3] = 1;
		for (int i = 3; i <= N; i++) {

			if (i % 3 == 0 && i % 2 == 0) {
				DP[i] = Math.min(Math.min(DP[i / 3], DP[i / 2]), DP[i - 1]) + 1;
			} else if (i % 2 == 0) {
				DP[i] = Math.min(DP[i / 2], DP[i - 1]) + 1;
			} else if (i % 3 == 0) {
				DP[i] = Math.min(DP[i / 3], DP[i - 1]) + 1;
			} else {
				DP[i] = DP[i - 1] + 1;
			}
		}
		sb.append(DP[N]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}
}