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
	static int input[][];
	static boolean visit[];
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		input = new int[N][N];
		visit = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void dfs(int depth, int idx) {
		if(depth == N/2) {
			int sum = 0;
			int sum2 =0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i==j) continue;
					if(visit[i] && visit[j]) {
						sum += input[i][j];
					}
					else if(!visit[i] && !visit[j]) {
						sum2 += input[i][j];
					}
				}
			}
			if(Math.abs(sum - sum2) < result) result = Math.abs(sum - sum2);
			return;
		}
		for(int i=idx; i<N; i++) {
			if(depth==0 && i>N/2) break;
			if(visit[i]) continue;
			visit[i] =true;
			dfs(depth+1, i);
			visit[i] = false;
		}
	}
	
}