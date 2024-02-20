import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	static int N, W, H;

	static int[][] map;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			result = Integer.MAX_VALUE;
			dfs(0, map);
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int[][] pre) { // 1 ~ H열 중에 한개 선택해서 제거한 뒤 깊이탐색
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (pre[i][j] > 0)
					count++;
			}
		}
		if (result > count)
			result = count;
		if (result == 0) {
			return;
		}
		if (depth == N) {
			return;
		}

		int[][] cur = new int[H][W];
		for (int i = 0; i < H; i++) {
			cur[i] = pre[i].clone();
		}

		for (int i = 0; i < W; i++) {
			int row = -1, col = i;
			for (int k = 0; k < H; k++) {
				if (cur[k][i] > 0) {
					row = k;
					break;
				}
			}
			if (row != -1) {
				int[][] next = remove(row, col, cur[row][col], cur);
				down(next);
				dfs(depth + 1, next);
			}
		}
	}

	static int[][] remove(int row, int col, int num, int[][] pre) { // 폭발작용

		int[][] cur = new int[H][W];
		for (int i = 0; i < H; i++) {
			cur[i] = pre[i].clone();
		}
		cur[row][col] = -1;
		num--;

		// 상
		for (int i = Math.max(0, row - num); i < row; i++) {
			if (cur[i][col] > 0) {
				cur = remove(i, col, cur[i][col], cur);
			}
		}

		// 하
		for (int i = Math.min(H - 1, row + num); i > row; i--) {
			if (cur[i][col] > 0) {
				cur = remove(i, col, cur[i][col], cur);
			}
		}

		// 좌
		for (int i = Math.max(0, col - num); i < col; i++) {
			if (cur[row][i] > 0) {
				cur = remove(row, i, cur[row][i], cur);
			}
		}
		// 우
		for (int i = Math.min(W - 1, col + num); i > col; i--) {
			if (cur[row][i] > 0) {
				cur = remove(row, i, cur[row][i], cur);
			}
		}

		return cur;
	}

	static void down(int[][] cur) { // 중력작용
		for (int j = 0; j < W; j++) {
			for (int i = H - 2; i >= 0; i--) {
				if (cur[i][j] != 0 && cur[i][j] != -1) {
					int idx = i;
					while (idx < H - 1 && cur[idx + 1][j] == -1) {
						cur[idx + 1][j] = cur[idx][j];
						cur[idx][j] = -1;
						idx++;
					}
				}
				if (cur[i][j] == 0)
					break;
				if (cur[i][j] == -1) {
					continue;
				}
			}
		}
	}
}