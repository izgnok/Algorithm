import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;

	static int[][] map;
	static Node[] rotate;
	static int[] check;
	static boolean[] visit;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		rotate = new Node[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			rotate[i] = new Node(r, c, s);
		}

		visit = new boolean[K];
		check = new int[K];
		result = Integer.MAX_VALUE;
		dfs(0);

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth) { // 순열
		if (depth == K) {
			move(); // 회전
			return;
		}

		for (int i = 0; i < K; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			check[depth] = i;
			dfs(depth + 1);
			visit[i] = false;
		}
	}

	static void move() {
		int[][] copy = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			copy[i] = map[i].clone();
		}

		for (int k = 0; k < K; k++) {
			Node node = rotate[check[k]];
			int r = node.r;
			int c = node.c;
			int S = node.s;

			for (int s = 1; s <= S; s++) {
				// 오른쪽으로
				int upTmp = copy[r - s][c + s];
				for (int j = c + s; j > c - s; j--) {
					copy[r - s][j] = copy[r - s][j - 1];
				}

				// 아래로
				int rightTmp = copy[r + s][c + s];
				for (int i = r + s; i > r - s; i--) {
					copy[i][c + s] = copy[i - 1][c + s];
				}
				copy[r - s + 1][c + s] = upTmp;

				// 왼쪽으로
				int leftTmp = copy[r + s][c - s];
				for (int j = c - s; j < c + s; j++) {
					copy[r + s][j] = copy[r + s][j + 1];
				}
				copy[r + s][c + s - 1] = rightTmp;

				// 위로
				for (int i = r - s; i < r + s; i++) {
					copy[i][c - s] = copy[i + 1][c - s];
				}
				copy[r + s - 1][c - s] = leftTmp;
			}

		}

		sum(copy); // 최소값구하기
	}

	static void sum(int[][] cur) {
		for (int i = 1; i <= N; i++) {
			int min = 0;
			for (int j = 1; j <= M; j++) {
				min += cur[i][j];
			}
			if (result > min)
				result = min;
		}
	}

	static class Node {
		int r, c, s;

		Node(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}