import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			Food[] input = new Food[N+1];
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				input[i] = new Food(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			
			//DP ? 분기한정법?
			int[][] DP = new int[N+1][W+1];
			
			for(int i=1; i<=N; i++) { // 현재 물건
				for(int j=1; j<=W; j++) { // 현재 무게
					if(j >= input[i].weight) {
						DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-input[i].weight] + input[i].score);
					}
					else {
						DP[i][j] = DP[i-1][j];
					}
				}
			}
			sb.append("#" + test_case + " " + DP[N][W] + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static class Food {
		int score, weight;
		
		Food(int score, int weight) {
			this.score = score;
			this.weight = weight;
		}
	}
}