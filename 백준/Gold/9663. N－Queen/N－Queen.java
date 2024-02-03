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
	static int N;
	static int Result = 0;
	static boolean[] col_visit;
	static int[][] diag_visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		col_visit = new boolean[N];
		diag_visit = new int[N][N];

		dfs(0);

		sb.append(Result + "");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth) {
		if (depth == N) { 
			Result++;
			return;
		}

		// depth: 깊이이면서 현재 놓아야하는 행
		for (int j = 0; j < N; j++) {
			// 해당 열에 놓을 수 없는 경우
			if (col_visit[j])
				continue; 
			if (diag_visit[depth][j] > 0)
				continue; 
			
			// 해당 열 체킹
			col_visit[j] = true;
			// 대각 체킹
			diag_check(depth, j);
			//dfs 탐색
			dfs(depth + 1);
			//체킹 해제
			col_visit[j] = false;
			diag_cancel(depth, j);
		}
	}
	
	static void diag_check(int idx, int jdx) {
		for(int i=idx+1; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i+j == idx+jdx) {
					diag_visit[i][j]++;
				}
				else if(j-i == jdx - idx) {
					diag_visit[i][j]++;
				}
			}
		}
	}
	
	static void diag_cancel(int idx, int jdx) {
		for(int i=idx+1; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i+j == idx+jdx) {
					diag_visit[i][j]--;
				}
				else if(j-i == jdx - idx) {
					diag_visit[i][j]--;
				}
			}
		}
	}
}