import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;

	static int result = 0;
	static boolean[] check;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		check = new boolean[26];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		visit[0][0] = true;
		check[map[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int idx, int jdx, int depth) {
		int word = map[idx][jdx] - 'A';
		int direct[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }; // 상, 하, 우, 좌

		for (int i = 0; i < 4; i++) {
			int row = idx + direct[i][0];
			int col = jdx + direct[i][1];

			if (row >= 0 && row < N && col >= 0 && col < M) { // 경계안일떄
				word = map[row][col] - 'A';
				if(visit[row][col]) continue; // 이미 방문한곳은 패스
				if(check[word]) continue; // 방문한 단어는 패스
				
				visit[row][col] = true;
				check[word] = true;
				dfs(row,col, depth+1);
				visit[row][col] = false;
				check[word] = false;
			}
		}
		
		if(result < depth) result = depth;
	}

	static class Node {
		int x, y, count;

		Node(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
}