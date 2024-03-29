import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int map[][];
	static int result = 0;
	static int visit[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visit = new int[N][N];
		dfs(0, 0, 0, 0);
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int row, int col, int count) {
		if (depth == N * 2 - 1) { // 끝까지 도착
			if (result < count)
				result = count;
			return;
		}

		if ((N * 2 - 2) - depth + count < result) { // 유망하지 않음
			return;
		}

		int next_row = Math.max(0, depth + 1 - (N-1)); // 다음 대각선찾기
		int next_col = Math.min(N - 1, depth + 1);

		if (map[row][col] != 0 && visit[row][col] == 0) {
			// 놓는 경우
			fill(row,col);
			dfs(depth + 1, next_row, next_col, count + 1);
			remove(row,col);
		}
		// 놓지 않는 경우
		if (col != 0) {
			if (row != N - 1) {
				dfs(depth, row + 1, col - 1, count);
			} else {
				dfs(depth + 1, next_row, next_col, count);
			}
		} else {
			dfs(depth + 1, next_row, next_col, count);
		}
	}
	
	static void fill(int row, int col) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i + j == row + col) {
					visit[i][j]++;
				} else if (j - i == col - row) {
					visit[i][j]++;
				}
			}
		}
	}

	static void remove(int row, int col) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i + j == row + col) {
					visit[i][j]--;
				} else if (j - i == col - row) {
					visit[i][j]--;
				}
			}
		}
	}
}