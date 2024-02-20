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
	static int N, M;
	static int R, C, L;

	static int map[][];
	static boolean visit[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visit = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs();
			int result = 0;
			for (int i = 0; i < N; i++) { // 주어진 시간안에 방문 가능한 경우
				for (int j = 0; j < M; j++) {
					if (visit[i][j])
						result++;
				}
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		q.add(new Node(R, C, 1));
		visit[R][C] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.time == L)
				continue;

			for (int i = 0; i < 4; i++) {
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row >= 0 && row < N && col >= 0 && col < M) {
					if (visit[row][col]) // 이미 방문한 경우
						continue;
					if (map[row][col] == 0) // 경로가 없는 경우
						continue;

					boolean possible = true;

					if (i == 0) { // 위로올라갈때
						if (map[node.x][node.y] == 3 || map[node.x][node.y] == 5 || map[node.x][node.y] == 6)
							possible = false;
						if (map[row][col] == 3 || map[row][col] == 4 || map[row][col] == 7)
							possible = false;
					} else if (i == 1) { // 아래로 내려갈때
						if (map[node.x][node.y] == 3 || map[node.x][node.y] == 4 || map[node.x][node.y] == 7)
							possible = false;
						if (map[row][col] == 3 || map[row][col] == 5 || map[row][col] == 6)
							possible = false;
					} else if (i == 2) { // 좌로 이동
						if (map[node.x][node.y] == 2 || map[node.x][node.y] == 4 || map[node.x][node.y] == 5)
							possible = false;
						if (map[row][col] == 2 || map[row][col] == 6 || map[row][col] == 7)
							possible = false;
					} else { // 우로 이동
						if (map[node.x][node.y] == 2 || map[node.x][node.y] == 6 || map[node.x][node.y] == 7)
							possible = false;
						if (map[row][col] == 2 || map[row][col] == 4 || map[row][col] == 5)
							possible = false;
					}

					if (possible) { // 이동 가능한 경우
						visit[row][col] = true;
						q.add(new Node(row, col, node.time + 1));
					}
				}
			}
		}
	}

	static class Node {
		int x, y, time;

		Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}