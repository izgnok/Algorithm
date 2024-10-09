import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int shark_x = 0, shark_y = 0;
	static int shark_level = 2;
	static int food_count = 0;
	static int N;
	static int result = 0;
	static int[][] map;
	static int min_dist, min_shark_x, min_shark_y, min_food_x, min_food_y;
	static boolean no;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark_x = i;
					shark_y = j;
					map[i][j] = -1;
				}
			}
		}

		while (true) {
			min_dist = Integer.MAX_VALUE;
			no = true;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] < shark_level && map[i][j] > 0) {
						bfs(i, j);
					}
				}
			}
			if (no)
				break;
			else 
				food_count++;
			if(food_count == shark_level) {
				food_count = 0;
				shark_level++;
			}
			result += min_dist;
			shark_x = min_shark_x;
			shark_y = min_shark_y;
			map[min_food_x][min_food_y] = -1;
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int bfs(int a, int b) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(a, b, 0));

		int[][] direct = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
		boolean[][] visit = new boolean[N][N];
		visit[a][b] = true;
		while (!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			int dist = node.dist;
			if (x == shark_x && y == shark_y) {
				if (min_dist > dist) {
					min_dist = dist;
					min_shark_x = a;
					min_shark_y = b;
					min_food_x = a;
					min_food_y = b;
					no = false;
				}
				continue;
			}
			if(dist >= min_dist) continue;

			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];
				if (row >= 0 && row < N && col >= 0 && col < N) {
					if (map[row][col] <= shark_level) {
						if(!visit[row][col]) q.add(new Node(row, col, dist+1));
						visit[row][col] = true;
					}
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	static class Node {
		int x, y, dist;

		Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}