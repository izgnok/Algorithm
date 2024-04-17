import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int DP_max[][];
	static int DP_min[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());

		DP_max = new int[N][3];
		DP_min = new int[N][3];

		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		DP_max[0][0] = a;
		DP_max[0][1] = b;
		DP_max[0][2] = c;
		DP_min[0][0] = a;
		DP_min[0][1] = b;
		DP_min[0][2] = c;

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			// 최대값 구하기
			DP_max[i][0] = a + Math.max(DP_max[i-1][0], DP_max[i-1][1]);
			DP_max[i][1] = b + Math.max(Math.max(DP_max[i-1][0], DP_max[i-1][1]), DP_max[i-1][2]);
			DP_max[i][2] = c + Math.max(DP_max[i-1][1], DP_max[i-1][2]);
			// 최소값 구하기
			DP_min[i][0] = a + Math.min(DP_min[i-1][0], DP_min[i-1][1]);
			DP_min[i][1] = b + Math.min(Math.min(DP_min[i-1][0], DP_min[i-1][1]), DP_min[i-1][2]);
			DP_min[i][2] = c + Math.min(DP_min[i-1][1], DP_min[i-1][2]);
		}
		int max = Math.max(Math.max(DP_max[N - 1][0], DP_max[N - 1][1]), DP_max[N - 1][2]);
		int min = Math.min(Math.min(DP_min[N - 1][0], DP_min[N - 1][1]), DP_min[N - 1][2]);
		sb.append(max).append(" ").append(min);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}