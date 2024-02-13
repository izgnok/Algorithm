import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int A[], B[];
	static int win, lose;
	static boolean visit[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int test_case = 0;
		while (test_case++ < T) {
			A = new int[9]; // 규영
			B = new int[9]; // 인영
			boolean[] check = new boolean[19];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				check[A[i]] = true;
			}
			int idx = 0;
			for (int i = 1; i < 19; i++) {
				if (!check[i])
					B[idx++] = i;
			}

			win = 0;
			lose = 0;
			visit = new boolean[9];
			dfs(0, 0, 0);

			sb.append("#" + test_case + " ");
			sb.append(win + " " + lose + "\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int A_score, int B_score) {
		if (depth == 9) {
			if (A_score > B_score) {
				win++;
			} else if (A_score < B_score) {
				lose++;
			} else {
			}
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (visit[i])
				continue;

			int a_score = A_score;
			int b_score = B_score;
			if (A[depth] > B[i]) {
				a_score += A[depth] + B[i];
			} else {
				b_score += A[depth] + B[i];
			}
			visit[i] = true;
			dfs(depth + 1, a_score, b_score);
			visit[i] = false;
		}
	}
}