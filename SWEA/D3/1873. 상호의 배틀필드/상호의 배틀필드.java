import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static char[][] map;
	static int x, y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int test_case = 0;
		int T = Integer.parseInt(st.nextToken());

		while (test_case++ < T) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == '<' || map[i][j] == '>' || map[i][j] == '^' || map[i][j] == 'v') {
						x = i;
						y = j;
					}
				}
			}

			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());

			String str = br.readLine();
			for (int i = 0; i < K; i++) {
				char cmd = str.charAt(i);
				if (cmd == 'U') {
					up();
				} else if (cmd == 'D') {
					down();
				} else if (cmd == 'L') {
					left();
				} else if (cmd == 'R') {
					right();
				} else {
					shoot();
				}
			}

			sb.append("#" + test_case + " ");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void up() {
		if (x - 1 >= 0 && map[x - 1][y] == '.') {
			map[x][y] = '.';
			map[x - 1][y] = '^';
			x--;
		} else
			map[x][y] = '^';
	}

	static void down() {
		if (x + 1 < N && map[x + 1][y] == '.') {
			map[x][y] = '.';
			map[x + 1][y] = 'v';
			x++;
		} else
			map[x][y] = 'v';
	}

	static void left() {
		if (y - 1 >= 0 && map[x][y - 1] == '.') {
			map[x][y] = '.';
			map[x][y - 1] = '<';
			y--;
		} else
			map[x][y] = '<';
	}

	static void right() {
		if (y + 1 < M && map[x][y + 1] == '.') {
			map[x][y] = '.';
			map[x][y + 1] = '>';
			y++;
		} else
			map[x][y] = '>';
	}

	static void shoot() {
		if (map[x][y] == '^') {
			int row = x - 1;
			while (row >= 0) {
				if (map[row][y] == '*') { // 벽
					map[row][y] = '.';
					break;
				} else if (map[row][y] == '#') { // 강철
					break;
				} else {
					row--;
				}
			}
		} else if (map[x][y] == 'v') {
			int row = x + 1;
			while (row < N) {
				if (map[row][y] == '*') { // 벽
					map[row][y] = '.';
					break;
				} else if (map[row][y] == '#') { // 강철
					break;
				} else {
					row++;
				}
			}

		} else if (map[x][y] == '<') {
			int col = y - 1;
			while (col >= 0) {
				if (map[x][col] == '*') { // 벽
					map[x][col] = '.';
					break;
				} else if (map[x][col] == '#') { // 강철
					break;
				} else {
					col--;
				}
			}

		} else if (map[x][y] == '>') {
			int col = y + 1;
			while (col < M) {
				if (map[x][col] == '*') { // 벽
					map[x][col] = '.';
					break;
				} else if (map[x][col] == '#') { // 강철
					break;
				} else {
					col++;
				}
			}

		} else {
			return;
		}
	}
}