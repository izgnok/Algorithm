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

public class Main {

	static int[][] map = new int[10][10];
	static int[] paper = new int[6];
	static int result = Integer.MAX_VALUE;

	static int one_count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					one_count++;
			}
		}

		Arrays.fill(paper, 5);
		dfs(0, 0, 0);
		if (result == Integer.MAX_VALUE)
			result = -1;
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int count, int idx, int jdx) {
		if (one_count == 0) {
			if (result > count) {
				result = count;
			}
			return;
		}

		if (result <= count)
			return;
		if (idx >= 10 || jdx >= 10)
			return;

		if (map[idx][jdx] == 0) {
			if (jdx < 9) {
				dfs(count, idx, jdx + 1);
			} else {
				dfs(count, idx + 1, 0);
			}
			return;
		}

		for (int k = 5; k > 0; k--) {
			if (paper[k] > 0) {
				if (square(idx, jdx, k)) {
					fill(idx, jdx, k, 0);
					paper[k]--;
					one_count -= k * k;
					if (jdx < 10 - k)
						dfs(count + 1, idx, jdx + k);
					else {
						dfs(count + 1, idx + 1, 0);
					}
					paper[k]++;
					one_count += k * k;
					fill(idx, jdx, k, 1);
				}
			}
		}
	}

	static boolean square(int idx, int jdx, int N) {
		if (idx + N > 10 || jdx + N > 10) // 종이가 경계밖일때
			return false;

		for (int i = idx; i < idx + N; i++) {
			for (int j = jdx; j < jdx + N; j++) {
				if (map[i][j] == 0) // 덮을수 없는 구역일때
					return false;
			}
		}
		return true;
	}

	static void fill(int idx, int jdx, int N, int num) {
		for (int i = idx; i < idx + N; i++) {
			for (int j = jdx; j < jdx + N; j++) {
				map[i][j] = num;
			}
		}
	}
}