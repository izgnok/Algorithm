import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(st.nextToken());
		while(T>0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int[] input = new int[N+1];
			for(int i=1; i<=N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			int[][] DP_G = new int[N+1][N+1]; // 근우
			int[][] DP_M = new int[N+1][N+1]; // 명우
		
			for(int j=1; j<=N; j++) {
				for(int i=1; i<=N ; i++) {
					if(j==1) {
						DP_G[i][1] = input[i];
						DP_M[i][1] = 0;
					}
					else if(j==2 && i!=1) {
						DP_G[i][2] = Math.max(input[i], input[i-1]);
						DP_M[i][2] = Math.min(input[i], input[i-1]);
					}
					else {
						if(i<j) continue;
						DP_G[i][j] = Math.max(input[i] + DP_M[i-1][j-1], input[i-(j-1)] + DP_M[i][j-1]);
						if(input[i] + DP_M[i-1][j-1] > input[i-(j-1)] + DP_M[i][j-1]) {
							DP_M[i][j] = DP_G[i-1][j-1];
						}
						else {
							DP_M[i][j] = DP_G[i][j-1];
						}
					}
				}
			}
			sb.append(DP_G[N][N]).append("\n"); // 근우 점수
			T--;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}