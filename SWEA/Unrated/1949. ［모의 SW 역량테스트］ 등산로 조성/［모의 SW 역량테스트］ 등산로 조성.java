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
	static int N, K;

	static int[][] map;
	static boolean[][] visit;
	static int result = 0;
	static boolean chance;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			visit = new boolean[N][N];
			result = 0;
			int max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > max)
						max = map[i][j];
				}
			}

			for (int i = 0; i < N; i++) { // 각 지점에서 탐색할수있는 개수 == 경로길이
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) {
						chance = true;
						visit[i][j] = true;
						dfs(new Node(i, j, max), 1);
						visit[i][j] = false;
					}
				}
			}

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(Node node, int depth) {
		if (result < depth)
			result = depth;

		if (node.pre == 0)
			return;

		int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
		for (int i = 0; i < 4; i++) {
			int row = node.x + direct[i][0];
			int col = node.y + direct[i][1];

			if (row >= 0 && row < N && col >= 0 && col < N) {
				if (visit[row][col])
					continue;

				if (map[row][col] < node.pre) {
					visit[row][col] = true;
					dfs(new Node(row, col, map[row][col]), depth + 1);
					visit[row][col] = false;
				} else {
					if (chance) {
						chance = false;
						visit[row][col] = true;
						if (map[row][col] - K < node.pre) {
							dfs(new Node(row, col, node.pre - 1), depth + 1);
						}
						visit[row][col] = false;
						chance = true;
					}
				}
			}
		}
	}

	static class Node {
		int x, y, pre;

		Node(int x, int y, int pre) {
			this.x = x;
			this.y = y;
			this.pre = pre;
		}
	}
}