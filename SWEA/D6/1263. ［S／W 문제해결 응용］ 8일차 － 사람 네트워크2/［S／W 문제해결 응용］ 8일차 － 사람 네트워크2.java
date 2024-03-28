import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int cost[][];
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			cost = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cost[i][j] = Integer.parseInt(st.nextToken());

					if (cost[i][j] == 0 && i != j) {
						cost[i][j] = INF;
					}
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (cost[i][j] == 1)
							continue;
                        
                        if (i==j)
                            continue;

						if (cost[i][k] == INF || cost[k][j] == INF)
							continue;
						cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
					}
				}
			}

			int min = INF;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					if (cost[i][j] == INF)
						continue;
					sum += cost[i][j];
				}
				if (min > sum)
					min = sum;
			}
			sb.append("#" + test_case + " " + min + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}