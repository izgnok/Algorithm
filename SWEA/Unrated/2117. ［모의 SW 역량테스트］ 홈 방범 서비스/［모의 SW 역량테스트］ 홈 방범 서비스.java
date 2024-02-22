import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, result;

	static int map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			int home = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						home++;
					}
				}
			}

			int max = M * home;

			result = 0;
			for (int k = N + 1; k > 0; k--) {
				if (max >= (k * k) + (k - 1) * (k - 1)) {
					result = simulation(k);
					if ((k * k) + (k - 1) * (k - 1) <= result * M) {
						break;
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int simulation(int k) {
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int tmp = Sum(i, j, k);
				if (max < tmp)
					max = tmp;
			}
		}
		return max;
	}

	static int Sum(int x, int y, int k) {
		// 마름모 범위 안에 집 개수 구하기
		int count = 0;
		int x_start = Math.max(0, x - (k - 1));
		int x_end = Math.min(N, x + k);
		int y_start = Math.max(0, y - (k - 1));
		int y_end = Math.min(N, y + k);

		for (int i = x_start; i < x_end; i++) {
			for (int j = y_start; j < y_end; j++) {
				if (map[i][j] == 0)
					continue;
				int d = dist(x, y, i, j);
				if (d < k) {
					count++;
				}
			}
		}

		return count;
	}

	static int dist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}