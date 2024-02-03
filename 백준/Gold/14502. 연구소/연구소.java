import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int Result = 0;
	static int[][] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);

		sb.append(Result + "");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth) {
		if (depth == 3) {
			bfs();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (input[i][j] == 1)
					continue;
				if (input[i][j] == 2)
					continue;
				input[i][j] = 1;
				dfs(depth + 1);
				input[i][j] = 0;
			}
		}
	}

	static void bfs() {
		Queue<Node> Q = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (input[i][j] == 2) {
					Node newNode = new Node(i, j);
					Q.add(newNode); // 2가 처음 나온 지점부터 탐색
				}
			}
		}

		int[][] tmp = new int[N][M]; // 안전영역을 계산하기 위한 배열, 원본 배열 유지를 위해서 임시로 생성
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = input[i][j];
			}
		}

		int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우 벽이 없는 방향 탐색
		while (!Q.isEmpty()) {
			Node node = Q.poll();
			int i = node.x;
			int j = node.y;

			for (int k = 0; k < 4; k++) {
				int row = i + direct[k][0];
				int col = j + direct[k][1];
				if (row >= 0 && row < N && col >= 0 && col < M) {
					if (tmp[row][col] == 0) { // 이미 방문해준것도 체크
						Q.add(new Node(row, col));
						tmp[row][col] = 2; // 벽이 아닌 곳은 바이러스
					}
				}
			}
		}

		int sum = 0;
		for (int i = 0; i < N; i++) { // 안전영역 합 구하기
			for (int j = 0; j < M; j++) {
				if (tmp[i][j] == 0) {
					sum++;
				}
			}
		}
		if (Result < sum)
			Result = sum;
	}

	static class Node { // 좌표를 한번에 저장하기 위한 객체
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}