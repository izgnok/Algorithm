import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] memory = new int[N];
		for (int i = 0; i < N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int[] cost = new int[N];
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		int DP[] = new int[10001];
		for (int i = 0; i < N; i++) {
			for (int j = 10000; j >= 0; j--) {
				if (j >= cost[i]) {
					DP[j] = Math.max(DP[j], DP[j - cost[i]] + memory[i]);
				}
			}
		}

		for (int i = 0; i <= 10000; i++) {
			if (DP[i] >= M) {
				sb.append(i);
				break;
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}