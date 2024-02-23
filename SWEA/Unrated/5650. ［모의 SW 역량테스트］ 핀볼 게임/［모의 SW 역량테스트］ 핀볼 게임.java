import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, result;

	static int[][] map;
	static List<Loc> start;
	static List<Loc> end;
	static List<List<Loc>> hole;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			start = new ArrayList<>();
			end = new ArrayList<>();
			hole = new ArrayList<>();
			for (int i = 0; i <= 10; i++) {
				hole.add(new ArrayList<>());
			}

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == -1) { // 블랙홀
						end.add(new Loc(i, j));
					}

					else if (map[i][j] == 0) { // 빈칸
						start.add(new Loc(i, j));
					} else if (map[i][j] >= 1 && map[i][j] <= 5) { // 블록

					} else { // 웜홀
						hole.get(map[i][j]).add(new Loc(i, j));
					}
				}
			}
			result = 0;
			for (int i = 0; i < start.size(); i++) {
				for (int j = 0; j < 4; j++) {
					Loc next = start.get(i);
					int count = simulation(next, j);
					if (result < count) {
						result = count;
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int simulation(Loc start, int dir) {
		Loc cur = new Loc(start.x, start.y);
		int count = 0;

		while (true) {
			int row = cur.x + direct[dir][0];
			int col = cur.y + direct[dir][1];
			cur = new Loc(row, col);

			if (row < 0 || row >= N || col < 0 || col >= N) { // 벽에 부딪힘
				dir = change(dir, -1);
				count++;
			} else if (map[row][col] >= 1 && map[row][col] <= 5) { // 블럭에 부딪힘
				dir = change(dir, map[row][col]);
				count++;
			} else if (map[row][col] >= 6 && map[row][col] <= 10) { // 웜홀
				cur = wormHole(cur, map[row][col]);
			}

			if (cur.equals(start)) {
				break;
			}

			boolean gameset = false;
			for (int i = 0; i < end.size(); i++) {
				if (cur.equals(end.get(i))) {
					gameset = true;
					break;
				}
			}
			if (gameset)
				break;
		}
		return count;
	}

	static Loc wormHole(Loc cur, int num) { // 좌표변경
		for (int i = 0; i < hole.get(num).size(); i++) {
			Loc next = hole.get(num).get(i);
			if (cur.equals(next)) // 같은경우 패스
				continue;
			else {
				cur = next;
				break;
			}
		}
		return cur;
	}

	static int change(int direct, int block) { // 방향변경

		if (block == 1 && (direct == 1 || direct == 2)) {
			if (direct == 1) {
				direct = 3;
			} else {
				direct = 0;
			}

		} else if (block == 2 && (direct == 0 || direct == 2)) {
			if (direct == 0) {
				direct = 3;
			} else {
				direct = 1;
			}

		} else if (block == 3 && (direct == 0 || direct == 3)) {
			if (direct == 0) {
				direct = 2;
			} else {
				direct = 1;
			}

		} else if (block == 4 && (direct == 1 || direct == 3)) {
			if (direct == 1) {
				direct = 2;
			} else {
				direct = 0;
			}

		} else { // 벽
			if (direct == 0) {
				direct = 1;
			} else if (direct == 1) {
				direct = 0;
			} else if (direct == 2) {
				direct = 3;
			} else {
				direct = 2;
			}
		}
		return direct;
	}

	static class Loc {
		int x, y;

		Loc(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			int result = 17;
			result = 31 * result + this.x;
			result = 31 * result + this.y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Loc)) {
				return false;
			}
			Loc other = (Loc) obj;
			return this.x == other.x && this.y == other.y;
		}
	}
}