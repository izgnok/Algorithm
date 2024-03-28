import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] cost;
	static int[][] next;
	static Node[] list;
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			list = new Node[N + 3];
			for (int i = 1; i <= N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list[i] = new Node(x, y);
			}

			cost = new int[N + 3][N + 3];

			for (int i = 1; i <= N + 2; i++) {
				for (int j = 1; j <= N + 2; j++) {
					cost[i][j] = Math.abs(list[i].x - list[j].x) + Math.abs(list[i].y - list[j].y);

					if (i != j && cost[i][j] > 1000) {
						cost[i][j] = INF;
					}
				}
			}

			for (int k = 1; k <= N + 2; k++) {
				for (int i = 1; i <= N + 2; i++) {
					for (int j = 1; j <= N + 2; j++) {
						if (cost[i][k] == INF || cost[k][j] == INF) {
							continue;
						}
						if (i == j || i == k || j == k)
							continue;
						if (cost[i][j] >= cost[i][k] + cost[k][j]) {
							cost[i][j] = cost[i][k] + cost[k][j];
						}
					}
				}
			}
			
			if(cost[1][N+2] != INF) {
				sb.append("happy").append("\n");
			}
			else {
				sb.append("sad").append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}