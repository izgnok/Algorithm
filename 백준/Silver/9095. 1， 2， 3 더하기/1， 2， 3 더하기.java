import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		while (T > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] DP = new int[11];
			DP[1] = 1;
			DP[2] = 2;
			DP[3] = 4;
			if (N >= 4) {
				for (int i = 4; i <= N; i++) {
					DP[i] = DP[i - 1] + DP[i - 2] + DP[i - 3];
				}
			}
			sb.append(DP[N]).append("\n");
			T--;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}