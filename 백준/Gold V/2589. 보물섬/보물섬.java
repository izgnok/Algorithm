import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char map[][];
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'W')
					continue; // 바다인 경우 패스
				bfs(new Node(i, j, 0));
			}
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs(Node start) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(start);
		boolean visit[][] = new boolean[N][M];
		visit[start.x][start.y] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;
			int dist = node.dist;
			if(result < dist) result = dist;

			int direct[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];
				if (row >= 0 && row < N && col >= 0 && col < M) {
					if (visit[row][col])
						continue;
					if (map[row][col] == 'W') 
						continue;
					q.add(new Node(row, col, dist + 1));
					visit[row][col] = true;
				}
			}
		}
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