import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int result = 0;

	static Queue<Node> tomato = new ArrayDeque<>();
	static int after = 0;
	static int before = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					tomato.add(new Node(i, j));
				}
				if (map[i][j] == 0)
					before++;
			}
		}

		if (before > 0) {
			while (true) {
				grow();
				result++;
				if (tomato.isEmpty()) { // 더 이상 변화가 없는 경우
					if (after != before) // 전부 다 익지 않은 경우
						result = -1;
					else result--; // 마지막 시행 이전으로 돌림
					break;

				}
			}
		} else { // 처음부터 다 익어있던 경우
			result = 0;
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void grow() {
		int size = tomato.size();

		for (int k = 0; k < size; k++) {
			Node node = tomato.poll();
			int x = node.x;
			int y = node.y;

			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= M)
					continue;
				if (map[row][col] == -1 || map[row][col] == 1)
					continue;

				map[row][col] = 1;
				tomato.add(new Node(row, col));
				after++;
			}
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