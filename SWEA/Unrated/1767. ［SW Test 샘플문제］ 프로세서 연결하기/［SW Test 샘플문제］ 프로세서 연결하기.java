import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] map;
	static List<Node> cell;
	static int result;
	static int max;

	static int[][] direct = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			cell = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						cell.add(new Node(i, j));
					}
				}
			}

			result = Integer.MAX_VALUE;
			max = 0;
			dfs(0, 0);
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int count) {
		if (depth == cell.size()) {
			// 전선 길이 구하기
			sum(count);
			return;
		}

		Node node = cell.get(depth);
		int x = node.x;
		int y = node.y;

		if (x == 0 || x == N - 1 || y == 0 || y == N - 1) { // 가장자리에있는 경우
			dfs(depth + 1, count);
			return;
		}

		// 상
		if (fill(0, node)) {
			dfs(depth + 1, count + 1);
		}
		remove(0, node);

		// 하
		if (fill(1, node)) {
			dfs(depth + 1, count + 1);
		}
		remove(1, node);

		// 좌
		if (fill(2, node)) {
			dfs(depth + 1, count + 1);
		}
		remove(2, node);

		// 우
		if (fill(3, node)) {
			dfs(depth + 1, count + 1);
		}
		remove(3, node);

		// 셀에 연결되는 경우
/*		for (int i = 0; i < 4; i++) {
			int row = x + direct[i][0];
			int col = y + direct[i][1];
			if (row >= 0 && row < N && col >= 0 && col < N) {
				if (map[row][col] == 1) {
					dfs(depth + 1, count);
					break;
				}
			}
		}*/
		
		//셀에 연결되거나 연결안되는 경우
		dfs(depth + 1, count);

	}

	static boolean fill(int direct, Node std) {
		boolean possible = true;

		if (direct == 0) {
			for (int i = std.x - 1; i >= 0; i--) {
				if (map[i][std.y] == 1) {
					possible = false;
					continue;
				}
				if (map[i][std.y] <= -1)
					possible = false;
				map[i][std.y]--;

			}
		} else if (direct == 1) {
			for (int i = std.x + 1; i < N; i++) {
				if (map[i][std.y] == 1) {
					possible = false;
					continue;
				}
				if (map[i][std.y] <= -1)
					possible = false;

				map[i][std.y]--;
			}

		} else if (direct == 2) {
			for (int j = std.y - 1; j >= 0; j--) {
				if (map[std.x][j] == 1) {
					possible = false;
					continue;
				}
				if (map[std.x][j] <= -1)
					possible = false;

				map[std.x][j]--;
			}
		} else {
			for (int j = std.y + 1; j < N; j++) {
				if (map[std.x][j] == 1) {
					possible = false;
					continue;
				}
				if (map[std.x][j] <= -1)
					possible = false;
				map[std.x][j]--;
			}
		}
		return possible;
	}

	static void remove(int direct, Node std) {
		if (direct == 0) {
			for (int i = std.x - 1; i >= 0; i--) {
				if (map[i][std.y] == 1) {
					continue;
				}
				map[i][std.y]++;

			}
		} else if (direct == 1) {
			for (int i = std.x + 1; i < N; i++) {
				if (map[i][std.y] == 1) {
					continue;
				}
				map[i][std.y]++;
			}

		} else if (direct == 2) {
			for (int j = std.y - 1; j >= 0; j--) {
				if (map[std.x][j] == 1) {
					continue;
				}
				map[std.x][j]++;
			}
		} else {
			for (int j = std.y + 1; j < N; j++) {
				if (map[std.x][j] == 1) {
					continue;
				}
				map[std.x][j]++;
			}
		}
	}

	static void sum(int count) {
		int tmp = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == -1)
					tmp++;
			}
		}
		if (count > max) { // 연결된 코어개수가많으면 무조건 갱신
			result = tmp;
			max = count;
		} else if (count == max && result > tmp) { // 연결된 코어개수가 같으면 길이가 짧을때만 갱신
			result = tmp;
		}
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}