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
	static int N;

	static int map[][];
	static boolean dessert[];
	static int start_x, start_y;
	static int result;

	static int[][] direct = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } }; // 우하, 좌하, 좌상, 우상 (시계방향)

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
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dessert = new boolean[101]; // 해당 디저트 사용했는지 안했는지.
			result = -1; // 결과값

			for (int i = 0; i < N; i++) { // 임의의 한 정점을 시작점으로 잡는다.
				for (int j = 0; j < N; j++) {
					dessert[map[i][j]] = true;
					start_x = i; // 시작점 설정
					start_y = j;
					dfs(1, i, j, 0);
					dessert[map[i][j]] = false;

				}
			}

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int x, int y, int k) { // 시계방향으로 깊이탐색

		for (int i = k; i <= k + 1; i++) { // 우하, 좌하, 좌상, 우상 ( 사각형을 이루기 위해서는 현재 방향에서 최대 한번만 바꿀수 있음 )
			if (i == 4)
				break; // 마지막 방향인 경우 예외처리
			int row = x + direct[i][0];
			int col = y + direct[i][1];

			if (row >= 0 && row < N && col >= 0 && col < N) {
				if (row == start_x && col == start_y) { // 시작점에 다시 돌아오면 result 값 갱신
					if (result < depth) {
						result = depth;
					}
					return;
				}
				if (dessert[map[row][col]])
					continue; // 해당 디저트를 이미 먹은 경우
				dessert[map[row][col]] = true;
				dfs(depth + 1, row, col, i);
				dessert[map[row][col]] = false;
			}
		}
		return;
	}

}