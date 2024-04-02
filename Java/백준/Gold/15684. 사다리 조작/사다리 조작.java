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
	static boolean possible;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		check = new boolean[H + 1][N + 1];
		visit = new int[N + 1];
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			check[a][b] = true;
			visit[b]++;
		}

		result = Integer.MAX_VALUE;
		dfs(1, 1, 0);
		if (result != Integer.MAX_VALUE)
			sb.append(result);
		else
			sb.append("-1");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int row, int col, int cnt) {
		if (cnt > 3) {
			return;
		}
		if (row > H) {
			if (simulation() && result > cnt) {
				result = cnt;
			}
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

		// 이미설치되어있는 경우가 아니고, 양옆의 가로선이 없으며, 해당 열의 가로선이 홀수개인경우
		if (!check[row][col] && !check[row][col - 1] && !check[row][col + 1]) {
			// 사다리 설치
			check[row][col] = true;
			visit[col]++;
			dfs(next_row, next_col, cnt + 1);

			// 설치 x
			check[row][col] = false;
			visit[col]--;
			dfs(next_row, next_col, cnt);
		} else { // 기본적으로 설치 되어있는 경우
			dfs(next_row, next_col, cnt);
		}
	}

	static boolean simulation() {
		boolean flag = true;

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