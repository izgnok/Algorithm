import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		int test_case = 1;
		while(test_case<=T) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] input = new int[N][N];
			String str;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				str = st.nextToken();
				for(int j=0; j<N; j++) {
					input[i][j] = str.charAt(j) -'0';
				}
			}
			
			int cur = 0;
			int mid = N/2;
			int result = 0;
			
			for(int i=0; i<N; i++) {
				result+=input[i][mid];
				int t = cur;
				while(t>0) {
					result += input[i][mid-t];
					result += input[i][mid+t];
					t--;
				}
				if(i >= N/2) cur--;
				else cur++;
			}
			sb.append("#" + test_case + " " + result +'\n');
			test_case++;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}