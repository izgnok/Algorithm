import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, D;
	static int[][] map;

	static int result;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visit = new boolean[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int idx) { // 궁수 위치 선정
		if (depth == 3) {
			int count = game();
			if (result < count) {
				result = count;
			}
			return;
		}

		for (int i = idx; i < M; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			dfs(depth + 1, i + 1);
			visit[i] = false;
		}
	}

	static int game() { // 게임시작

		int count = 0;
		int[][] cur = new int[N][M];
		for (int i = 0; i < N; i++) {
			cur[i] = map[i].clone();
		}

		while (true) {
			count += attack(cur);

			int enemy = 0;
			for (int i = N - 1; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					if (cur[i][j] == 1) {
						enemy++;
						cur[i][j] = 0;
						if (i + 1 == N) {
							enemy--;
						} else {
							cur[i + 1][j] = 1;
						}
					}
				}
			}
			if (enemy == 0)
				break;
		}
		return count;
	}

	static int attack(int[][] cur) { // 공격하는 적 선정

		ArrayList<PriorityQueue<Node>> pq = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			pq.add(new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist));
		}
		
		int idx = 0;
		int min_i = N - D;
		if(min_i < 0) min_i = 0;
		for (int k = 0; k < M; k++) {
			if (!visit[k])
				continue; // 선택안된건 패스
			for (int j = 0; j < M; j++) {
				for (int i = N - 1; i >= min_i; i--) {
					if (cur[i][j] == 1) { // 적이있을때
						int d = dist(i, j, N, k);
						if (D >= d) { // 공격범위안일때
							pq.get(idx).add(new Node(i, j, d));
						}
					}
				}
			}
			idx++;
		}

		boolean[][] die = new boolean[N][M];
		int count = 0;
		
		for(int i=0; i<pq.size(); i++) {
			Node tmp = pq.get(i).peek();
			if(tmp==null) continue;
			if (!die[tmp.x][tmp.y])
				count++;
			die[tmp.x][tmp.y] = true;
			cur[tmp.x][tmp.y] = 0;
		}

		return count;
	}

	static int dist(int x1, int y1, int x2, int y2) { // 거리계산
		int row = Math.abs(x1 - x2);
		int col = Math.abs(y1 - y2);
		return row + col;
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