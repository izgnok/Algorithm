import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][][] DP = new int[N + 1][N + 1][3];
		int[][] input = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		DP[1][0][0] = 1; // (1,0) 잡아주기 
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (input[i][j] == 1) {
					DP[i][j][0] = 0;
					DP[i][j][1] = 0;
					DP[i][j][2] = 0;
					continue;
				}
				if (i == 1) { // 첫째행일
					DP[i][j][0] = DP[i][j-1][0];
					DP[i][j][1] = 0;
					DP[i][j][2] = 0;
				} else if (j == 1 || j == 2) { // 두번째 행 이상부터 첫번째, 두번째열일때 (갈수없음)
					DP[i][j][0] = 0;
					DP[i][j][1] = 0;
					DP[i][j][2] = 0;
				} else {
					// 가로로 배치되는 경우 
					DP[i][j][0] = DP[i][j-1][0] + DP[i][j-1][2];
	
					// 세로로 배치되는 경우 
					DP[i][j][1] = DP[i-1][j][1] + DP[i-1][j][2];
						
					// 대각으로 배치되는 경우 
					if(input[i-1][j] == 1 || input[i][j-1] == 1) DP[i][j][2] = 0;
					else DP[i][j][2] = DP[i-1][j-1][0] + DP[i-1][j-1][1] + DP[i-1][j-1][2];
					
				}
			}
		}
		int result = DP[N][N][0] + DP[N][N][1] + DP[N][N][2];
		sb.append(String.valueOf(result));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}