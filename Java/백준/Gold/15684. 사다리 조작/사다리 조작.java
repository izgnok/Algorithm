import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, H;
	static boolean check[][];
	static int visit[];
	static int result;
	static boolean finish;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		check = new boolean[H + 1][N + 1];
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			check[a][b] = true;
		}

		result = -1;
		finish = false;
		for (int i = 0; i <= 3; i++) { // 뽑을 최대 개수 ( 0 ~ 3 )
			dfs(1, 1, 0, i);
			if (finish) {
				result = i;
				break;
			}
		}

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int row, int col, int cnt, int max) {
		if (cnt == max) { // 최대개수 뽑은 경우
			finish = simulation();
			return;
		}
		if (row > H) {
			return;
		}

		int next_row = row;
		int next_col = col;
		if (next_col == N - 1) {
			next_row++;
			next_col = 1;
		} else {
			next_col++;
		}

		// 이미설치되어있는 경우가 아니고, 양옆의 가로선이 없는 경우
		if (!check[row][col] && !check[row][col - 1] && !check[row][col + 1]) {
			// 사다리 설치
			check[row][col] = true;
			dfs(next_row, next_col, cnt + 1, max);
			if (finish)
				return;

			// 설치 x
			check[row][col] = false;
			dfs(next_row, next_col, cnt, max);
		} else { // 기본적으로 설치 되어있는 경우
			dfs(next_row, next_col, cnt, max);
		}
	}

	static boolean simulation() {
		boolean flag = false;
		for (int i = 1; i <= N; i++) { // 출발점 1 ~ N
			int row = 1;
			int col = i;

			while (row <= H) {
				if (check[row][col]) {
					col = col + 1;
				} else if (check[row][col - 1]) {
					col = col - 1;
				}
				row++;
			}

			if (col == i) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		return flag;
	}
}