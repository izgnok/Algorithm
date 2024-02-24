import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, C, result;

	static int row_visit[];
	static int col_visit[];
	static int[][] map;
	static int[] honey1;
	static int[] honey2;
	static boolean[] check;
	static int honey1_max;
	static int honey2_max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			row_visit = new int[2];
			col_visit = new int[2];
			honey1 = new int[M];
			honey2 = new int[M];
			check = new boolean[M];
			result = 0;
			row_dfs(0, 0);
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void row_dfs(int idx, int cnt) { // 행선택
		if (cnt == 2) {
			// 열선택 (벌통선택)
			col_dfs(0, 0);
			return;
		}

		for (int i = idx; i < N; i++) {
			row_visit[cnt] = i;
			row_dfs(i + 1, cnt + 1);
		}
	}

	static void col_dfs(int jdx, int cnt) {
		if (cnt == 2) {
			// 꿀채취
			honey();
			return;
		}

		for (int j = jdx; j <= N - M; j++) {
			col_visit[cnt] = j;
			col_dfs(j, cnt + 1);
		}
	}

	static void honey() { // 벌통 세팅
		int row1 = row_visit[0];
		int col1 = col_visit[0];
		int row2 = row_visit[1];
		int col2 = col_visit[1];

		for (int i = 0; i < M; i++) {
			honey1[i] = map[row1][col1++];
			honey2[i] = map[row2][col2++];
		}

		honey1_max = 0;
		honey2_max = 0;
		get(0, honey1);
		get2(0, honey2);
		if (result < honey1_max + honey2_max) {
			result = honey1_max + honey2_max;
		}

		row1 = row_visit[0];
		col1 = col_visit[1];
		row2 = row_visit[1];
		col2 = col_visit[0];

		for (int i = 0; i < M; i++) {
			honey1[i] = map[row1][col1++];
			honey2[i] = map[row2][col2++];
		}

		honey1_max = 0;
		honey2_max = 0;
		get(0, honey1);
		get2(0, honey2);
		if (result < honey1_max + honey2_max) {
			result = honey1_max + honey2_max;
		}
	}

	static void get(int depth, int[] arr) { // 일꾼1
		if (depth == M) {
			int sum = 0;
			int cnt = 0;
			for (int i = 0; i < M; i++) {
				if (check[i]) {
					sum += arr[i] * arr[i];
					cnt += arr[i];
				}
			}
			if (cnt <= C && honey1_max < sum)
				honey1_max = sum;
			return;
		}
		check[depth] = true;
		get(depth + 1, arr);
		check[depth] = false;
		get(depth + 1, arr);
	}

	static void get2(int depth, int[] arr) { // 일꾼2
		if (depth == M) {
			int sum = 0;
			int cnt = 0;
			for (int i = 0; i < M; i++) {
				if (check[i]) {
					sum += arr[i] * arr[i];
					cnt += arr[i];
				}
			}
			if (cnt <= C && honey2_max < sum)
				honey2_max = sum;
			return;
		}

		check[depth] = true;
		get2(depth + 1, arr);
		check[depth] = false;
		get2(depth + 1, arr);
	}
}