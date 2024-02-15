import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] input;
	static int[][] result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int test_case = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N==0) break;

			input = new int[N][N];
			result = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					input[i][j] = Integer.parseInt(st.nextToken());
					result[i][j] = Integer.MAX_VALUE;
				}
			}

			dijkstra();
			sb.append("Problem " + test_case++ + ": ");
			sb.append(result[N - 1][N - 1]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		result[0][0] = input[0][0];
		pq.add(new Node(0, 0, result[0][0]));

		int[][] direct = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		while (!pq.isEmpty()) {
			Node tmp = pq.poll();
			for (int i = 0; i < 4; i++) {
				int row = tmp.x + direct[i][0];
				int col = tmp.y + direct[i][1];

				if (row >= 0 && row < N && col >= 0 && col < N) {
					if (tmp.cost + input[row][col] < result[row][col]) {
						result[row][col] = tmp.cost + input[row][col];
						pq.add(new Node(row, col, result[row][col]));
					}
				}
			}
		}
	}

	static class Node {
		int x, y, cost;

		Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
}