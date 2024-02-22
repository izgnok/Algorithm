import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, K;
	static int[][] map;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int result = 0;

			// 가로
			for (int i = 0; i < N; i++) {
				boolean possible = true;
				int high = map[i][0];
				visit = new boolean[N];

				for (int j = 1; j < N; j++) {
					if (map[i][j] == high) // 높이가 같은 경우
						continue;
					// 높이가 1낮은 경우
					else if (map[i][j] == high - 1) {
						int k = K;
						int tmp = high - 1;
						int jdx = j;
						while (k-- > 0) {
							if (jdx >= N) {
								possible = false;
								break;
							}
							if (visit[jdx]) {
								possible = false;
								break;
							}
							if (tmp != map[i][jdx]) {
								possible = false;
								break;
							}
							visit[jdx++] = true;
						}
						if (possible) {
							high = tmp;
							j = jdx - 1;
						} else {
							for (int t = j; t <= jdx - 1; t++) {
								visit[t] = false;
							}
							break;
						}
					}
					// 높이가 1높은 경우
					else if (map[i][j] == high + 1) {
						int k = K;
						int tmp = high;
						int jdx = j - 1;
						while (k > 0) {
							if (jdx < 0) {
								possible = false;
								break;
							}
							if (visit[jdx]) {
								possible = false;
								break;
							}
							if (tmp != map[i][jdx]) {
								possible = false;
								break;
							}
							visit[jdx--] = true;
							k--;
						}
						if (possible) {
							high = tmp + 1;
						} else {
							for (int t = j - 1; t >= j - K + k; t--) {
								visit[t] = false;
							}
							break;
						}
					} else {
						possible = false;
						break;
					}
				}
				if (possible)
					result++;
			}

			// 세로
			for (int j = 0; j < N; j++) {
				boolean possible = true;
				int high = map[0][j];
				visit = new boolean[N];

				for (int i = 1; i < N; i++) {
					if (map[i][j] == high) // 높이가 같은 경우
						continue;
					// 높이가 1낮은 경우
					else if (map[i][j] == high - 1) {
						int k = K;
						int tmp = high - 1;
						int idx = i;
						while (k-- > 0) {
							if (idx >= N) {
								possible = false;
								break;
							}
							if (visit[idx]) {
								possible = false;
								break;
							}
							if (tmp != map[idx][j]) {
								possible = false;
								break;
							}
							visit[idx++] = true;
						}
						if (possible) {
							high = tmp;
							i = idx - 1;
						} else {
							for (int t = i; t <= idx - 1; t++) {
								visit[t] = false;
							}
							break;
						}
					}
					// 높이가 1높은 경우
					else if (map[i][j] == high + 1) {
						int k = K;
						int tmp = high;
						int idx = i - 1;
						while (k > 0) {
							if (idx < 0) {
								possible = false;
								break;
							}
							if (visit[idx]) {
								possible = false;
								break;
							}
							if (tmp != map[idx][j]) {
								possible = false;
								break;
							}
							visit[idx--] = true;
							k--;
						}
						if (possible) {
							high = tmp + 1;
						} else {
							for (int t = i - 1; t >= i - K + k; t--) {
								visit[t] = false;
							}
							break;
						}
					} else {
						possible = false;
						break;
					}
				}
				if (possible)
					result++;
			}

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}