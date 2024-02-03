import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int result = 0;
	static int result2 = 0;
	static char[][] input;
	static char[][] input2;
	static boolean[][] visit;
	static boolean[][] visit2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		input = new char[N][N];
		visit = new boolean[N][N];
		input2 = new char[N][N];
		visit2 = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			String[] tmp = str.split("");
			for (int j = 0; j < N; j++) {
				input[i][j] = tmp[j].charAt(0);
				input2[i][j] = input[i][j];
				if (input2[i][j] == 'G')
					input2[i][j] = 'R';
			}
		}

		bfs();
		bfs2();

		sb.append(result).append(" ").append(result2);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		Deque<Node> dq = new ArrayDeque<>(); // 탐색할 좌표값 저장
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visit[i][j]) continue;
				
				dq.addLast(new Node(i, j));
				visit[i][j] = true;
				while(!dq.isEmpty()) {
					Node tmp = dq.pollFirst();
					int x = tmp.x;
					int y = tmp.y;
					
					int[][] dir = { { 0, 1 }, { 1, 0 }, {-1,0} ,{0,-1} };
					for (int k = 0; k < 4; k++) {
						int row = x + dir[k][0];
						int col = y + dir[k][1];
						if (row < N && col < N && row >=0 && col >=0) {
							if(input[i][j] == input[row][col]) {
								if(visit[row][col]) continue;
								dq.addLast(new Node(row,col));
								visit[row][col] = true;
							}
						}
					}
				}
				result++;
			}
		}
	}

	static void bfs2() {
		Deque<Node> dq = new ArrayDeque<>(); // 탐색할 좌표값 저장
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visit2[i][j]) continue;
				
				dq.addLast(new Node(i, j));
				visit2[i][j] = true;
				while(!dq.isEmpty()) {
					Node tmp = dq.pollFirst();
					int x = tmp.x;
					int y = tmp.y;
					
					int[][] dir = { { 0, 1 }, { 1, 0 }, {-1,0} ,{0,-1} };
					for (int k = 0; k < 4; k++) {
						int row = x + dir[k][0];
						int col = y + dir[k][1];
						if (row < N && col < N && row >=0 && col >=0) {
							if(input2[i][j] == input2[row][col]) {
								if(visit2[row][col]) continue;
								dq.addLast(new Node(row,col));
								visit2[row][col] = true;
							}
						}
					}
				}
				result2++;
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