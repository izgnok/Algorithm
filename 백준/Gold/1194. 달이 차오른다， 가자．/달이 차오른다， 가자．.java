import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;

	static int result = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		int idx = -1, jdx = -1; // 시작점
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '0') {
					idx = i;
					jdx = j;
				}
			}
		}

		bfs(idx, jdx);

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs(int idx, int jdx) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(idx, jdx, 0, 0));
		boolean[][][] visit = new boolean[N][M][64];
		visit[idx][jdx][0] = true;
		
		int[][] direct = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			int i = tmp.i;
			int j = tmp.j;
			int key = tmp.key;
			int cnt = tmp.cnt;

			if (map[i][j] == '1') {
				result = cnt;
				break;
			}

			for (int k = 0; k < 4; k++) {
				int row = i + direct[k][0];
				int col = j + direct[k][1];

				if (row >= 0 && row < N && col >= 0 && col < M) {
					if (!visit[row][col][key]) { // 방문한적없는경우
						if (map[row][col] >= 'A' && map[row][col] <= 'F') { // 열쇠문
							// 해당 열쇠를 가지고있는 경우
							if ((key & (1 << (map[row][col] - 'A'))) != 0) {
								visit[row][col][key] = true;
								q.add(new Node(row, col, key, cnt + 1));
							}
						} else if (map[row][col] >= 'a' && map[row][col] <= 'f') { // 열쇠
							int tmp_key = key | (1 << (map[row][col] - 'a'));
							visit[row][col][tmp_key] = true;
							q.add(new Node(row, col, tmp_key, cnt + 1));
						} else if (map[row][col] == '#') { // 벽
							continue;
						} else {
							visit[row][col][key] = true;
							q.add(new Node(row, col, key, cnt + 1));
						}
					}
				}
			}
		}
	}

	static class Node {
		int i, j, key, cnt;

		Node(int i, int j, int key, int cnt) {
			this.i = i;
			this.j = j;
			this.key = key;
			this.cnt = cnt;
		}
	}
}