import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] visit;
	static boolean[] key;
	static char[][] map;
	static int result;
	static int direct[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N + 2][M + 2];
			key = new boolean[26];

			for (int i = 0; i <= N + 1; i++) {
				map[i][0] = '.';
				map[i][M + 1] = '.';
			}
			for (int j = 0; j <= M + 1; j++) {
				map[0][j] = '.';
				map[N + 1][j] = '.';
			}
			for (int i = 1; i <= N; i++) {
				String str = br.readLine();
				for (int j = 1; j <= M; j++) {
					map[i][j] = str.charAt(j - 1);
				}
			}

			String str = br.readLine();
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '0')
					break;
				key[str.charAt(i) - 'a'] = true;
			}

			visit = new boolean[N + 2][M + 2];
			result = 0;
			bfs();
			sb.append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		List<Node> list = new ArrayList<>();
		q.add(new Node(0, 0));
		visit[0][0] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();

			for (int i = 0; i < 4; i++) {
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row < 0 || row >= N + 2 || col < 0 || col >= M + 2)
					continue;

				if (visit[row][col])
					continue;
				visit[row][col] = true;

				// 벽인 경우
				if (map[row][col] == '*')
					continue;
				// 열쇠인 경우
				else if (map[row][col] >= 'a' && map[row][col] <= 'z') {
					key[map[row][col] - 'a'] = true;
					q.add(new Node(row, col));

					// 방문했지만 못 열었던 문 탐색
					for (int k = list.size() - 1; k >= 0; k--) {
						Node li = list.get(k);
						if (key[map[li.x][li.y] - 'A']) {
							q.add(new Node(li.x, li.y));
							list.remove(k);
						}
					}
				}
				// 문인 경우
				else if (map[row][col] >= 'A' && map[row][col] <= 'Z') {
					// 열쇠가 있는 경우
					if (key[map[row][col] - 'A']) {
						q.add(new Node(row, col));
					}
					// 열쇠가 없는 경우
					else {
						list.add(new Node(row, col));
					}
				}
				// 문서인 경우
				else if (map[row][col] == '$') {
					q.add(new Node(row, col));
					result++;
				}

				// 빈칸인 경우
				else {
					q.add(new Node(row, col));
				}
			}
		}
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "x: " + x + " y: " + y;
		}
	}
}