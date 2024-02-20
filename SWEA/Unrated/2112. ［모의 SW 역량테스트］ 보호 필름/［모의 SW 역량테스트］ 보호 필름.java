import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, K;

	static int[][] map; // 원본
	static int[][] cur;
	static int result;
	static int max;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			cur = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					cur[i][j] = map[i][j];
				}
			}
			result = Integer.MAX_VALUE;
			if (K != 1) {
				dfs(0, 0);
			} else {
				result = 0;
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int count) {
		if (count >= result) // 유망하지 않은 경우
			return;

		if (depth == N) {
			// 성능 검사
			if (check()) {
				if (result > count)
					result = count;
			}
			return;
		}

		dfs(depth + 1, count); // 약물 투입 안함

		for (int i = 0; i < M; i++) { // A약물 투입
			cur[depth][i] = 0;
		}
		dfs(depth + 1, count + 1);
		// 복구
		for (int i = 0; i < M; i++) {
			cur[depth][i] = map[depth][i];
		}
		
		for (int i = 0; i < M; i++) { // B약물 투입
			cur[depth][i] = 1;
		}
		dfs(depth + 1, count + 1);
		// 복구
		for (int i = 0; i < M; i++) {
			cur[depth][i] = map[depth][i];
		}
	}

	static boolean check() { // 성능 검사
		for (int j = 0; j < M; j++) {
			int count = 1;
			for (int i = 0; i < N - 1; i++) {
				if (cur[i][j] == cur[i + 1][j]) {
					count++;
					if (count == K)
						break;
				} else {
					count = 1;
				}
			}
			if (count < K)
				return false;
		}
		return true;
	}
}