import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[][] room;
	static boolean[][] visit;
	static int N;
	static int result;
	static int start;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			room = new int[N][N];
			start = Integer.MAX_VALUE;
			result = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visit = new boolean[N][N];
					bfs(i, j);
				}
			}
			sb.append("#" + test_case + " " + start + " " + result + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs(int x, int y) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x, y, 1));
		visit[x][y] = true;

		int[][] direct = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
		while (!q.isEmpty()) {
			Node node = q.poll();
			if(result < node.depth) {
				start = room[x][y];
				result = node.depth;
			}
			else if(result == node.depth) {
				if(start > room[x][y])  start = room[x][y];
			}
			
			for(int i=0; i<4; i++) {
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];
				
				if(row>=0 && row<N && col >=0 && col < N) { // 크기 안에 존재하고
					if( room[row][col] == room[node.x][node.y] + 1) { // 값이 +1 차이이고
						if(!visit[row][col]) { // 아직 방문하지 않았다면
							q.add(new Node(row,col, node.depth+1));
							visit[row][col] = true;
						}
					}
				}
			}
		}
		
		
		
	}

	static class Node {
		int x, y, depth;

		Node(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
}