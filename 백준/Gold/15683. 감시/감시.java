import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static int N, M;

	static int[][] map;
	static int[][] copy;
	static List<Node> cctv;

	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		copy = new int[N][M];
		cctv = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copy[i][j] = map[i][j];
				if (map[i][j] > 0 && map[i][j] < 6) {
					cctv.add(new Node(i, j, map[i][j]));
				}
			}
		}

		dfs(0);
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth) {
		if (depth == cctv.size()) {
			sum(); // 사각지대 계산
			return;
		}

		int x = cctv.get(depth).x;
		int y = cctv.get(depth).y;
		int num = cctv.get(depth).num;

		if (num == 1) {
			// 상
			cctv_fill(x, y, 0);
			dfs(depth + 1);
			cctv_empty(x, y, 0);

			// 하
			cctv_fill(x, y, 1);
			dfs(depth + 1);
			cctv_empty(x, y, 1);

			// 좌
			cctv_fill(x, y, 2);
			dfs(depth + 1);
			cctv_empty(x, y, 2);

			// 우
			cctv_fill(x, y, 3);
			dfs(depth + 1);
			cctv_empty(x, y, 3);

		} else if (num == 2) {
			// 상하
			cctv_fill(x, y, 0);
			cctv_fill(x, y, 1);
			dfs(depth + 1);
			cctv_empty(x, y, 0);
			cctv_empty(x, y, 1);

			// 좌우
			cctv_fill(x, y, 2);
			cctv_fill(x, y, 3);
			dfs(depth + 1);
			cctv_empty(x, y, 2);
			cctv_empty(x, y, 3);

		} else if (num == 3) {
			// 상우
			cctv_fill(x, y, 0);
			cctv_fill(x, y, 3);
			dfs(depth + 1);
			cctv_empty(x, y, 0);
			cctv_empty(x, y, 3);

			// 우하
			cctv_fill(x, y, 3);
			cctv_fill(x, y, 1);
			dfs(depth + 1);
			cctv_empty(x, y, 3);
			cctv_empty(x, y, 1);

			// 하좌
			cctv_fill(x, y, 1);
			cctv_fill(x, y, 2);
			dfs(depth + 1);
			cctv_empty(x, y, 1);
			cctv_empty(x, y, 2);

			// 좌상
			cctv_fill(x, y, 2);
			cctv_fill(x, y, 0);
			dfs(depth + 1);
			cctv_empty(x, y, 2);
			cctv_empty(x, y, 0);

		} else if (num == 4) {
			// 좌상우
			cctv_fill(x, y, 2);
			cctv_fill(x, y, 0);
			cctv_fill(x, y, 3);
			dfs(depth + 1);
			cctv_empty(x, y, 2);
			cctv_empty(x, y, 0);
			cctv_empty(x, y, 3);

			// 상우하
			cctv_fill(x, y, 0);
			cctv_fill(x, y, 3);
			cctv_fill(x, y, 1);
			dfs(depth + 1);
			cctv_empty(x, y, 0);
			cctv_empty(x, y, 3);
			cctv_empty(x, y, 1);

			// 우하좌
			cctv_fill(x, y, 3);
			cctv_fill(x, y, 1);
			cctv_fill(x, y, 2);
			dfs(depth + 1);
			cctv_empty(x, y, 3);
			cctv_empty(x, y, 1);
			cctv_empty(x, y, 2);

			// 하좌상
			cctv_fill(x, y, 1);
			cctv_fill(x, y, 2);
			cctv_fill(x, y, 0);
			dfs(depth + 1);
			cctv_empty(x, y, 1);
			cctv_empty(x, y, 2);
			cctv_empty(x, y, 0);

		} else if (num == 5) {
			// 상하좌우
			cctv_fill(x, y, 0);
			cctv_fill(x, y, 1);
			cctv_fill(x, y, 2);
			cctv_fill(x, y, 3);
			dfs(depth + 1);
			cctv_empty(x, y, 0);
			cctv_empty(x, y, 1);
			cctv_empty(x, y, 2);
			cctv_empty(x, y, 3);
		}
	}

	static void cctv_fill(int x, int y, int dir) { // 시각 채우기 ( 상, 하, 좌 , 우 )
		if (dir == 0) {
			for (int i = x - 1; i >= 0; i--) {
				if (map[i][y] == 6)
					break;
				if (map[i][y] == 0) {
					copy[i][y]--;
				}
			}
		} else if (dir == 1) {
			for (int i = x + 1; i < N; i++) {
				if (map[i][y] == 6)
					break;
				if (map[i][y] == 0) {
					copy[i][y]--;
				}
			}
		} else if (dir == 2) {
			for (int i = y - 1; i >= 0; i--) {
				if (map[x][i] == 6)
					break;
				if (map[x][i] == 0) {
					copy[x][i]--;
				}
			}

		} else {
			for (int i = y + 1; i < M; i++) {
				if (map[x][i] == 6)
					break;
				if (map[x][i] == 0) {
					copy[x][i]--;
				}
			}
		}
	}

	static void cctv_empty(int x, int y, int dir) { // 복구

		if (dir == 0) {
			for (int i = x - 1; i >= 0; i--) {
				if (map[i][y] == 6)
					break;
				if (map[i][y] == 0) {
					copy[i][y]++;
				}
			}
		} else if (dir == 1) {
			for (int i = x + 1; i < N; i++) {
				if (map[i][y] == 6)
					break;
				if (map[i][y] == 0) {
					copy[i][y]++;
				}
			}
		} else if (dir == 2) {
			for (int i = y - 1; i >= 0; i--) {
				if (map[x][i] == 6)
					break;
				if (map[x][i] == 0) {
					copy[x][i]++;
				}
			}

		} else {
			for (int i = y + 1; i < M; i++) {
				if (map[x][i] == 6)
					break;
				if (map[x][i] == 0) {
					copy[x][i]++;
				}
			}
		}
	}

	static void sum() {
		int visible = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0)
					visible++;
			}
		}

		if (result > visible)
			result = visible;
	}

	static class Node {
		int x, y, num;

		Node(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

}