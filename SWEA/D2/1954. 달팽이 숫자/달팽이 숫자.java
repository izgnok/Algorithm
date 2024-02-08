import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int test_case = 1;
		
		while (test_case <= 10) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			int number = 1;
			int[][] result = new int[N + 1][N + 1];
			int idx = 1;
			int jdx = 1;
			int direct = 0; // 우 , 하, 좌, 상

			while (true) {
				result[idx][jdx] = number;
				if (number == N * N)
					break;
				
				if (direct == 0)
					jdx++;
				if (direct == 1)
					idx++;
				if (direct == 2)
					jdx--;
				if (direct == 3)
					idx--;

				if (idx < 1 || idx > N) {
					direct++;
					if(direct==4) direct=0;
					if (idx < 1)
						idx++;
					else
						idx--;
					
					if (direct == 0)
						jdx++;
					if (direct == 1)
						idx++;
					if (direct == 2)
						jdx--;
					if (direct == 3)
						idx--;
				} else if (jdx < 1 || jdx > N) {
					direct++;
					if(direct==4) direct=0;
					
					if (jdx < 1)
						jdx++;
					else
						jdx--;
					
					if (direct == 0)
						jdx++;
					if (direct == 1)
						idx++;
					if (direct == 2)
						jdx--;
					if (direct == 3)
						idx--;
				} else if (result[idx][jdx] != 0) {
					if (direct == 0)
						jdx--;
					if (direct == 1)
						idx--;
					if (direct == 2)
						jdx++;
					if (direct == 3)
						idx++;
					direct++;
					if(direct==4) direct=0;
					
					if (direct == 0)
						jdx++;
					if (direct == 1)
						idx++;
					if (direct == 2)
						jdx--;
					if (direct == 3)
						idx--;
				}
				number++;
			}
			
			sb.append("#"+ test_case + "\n");
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					sb.append(result[i][j] + " ");
				}
				sb.append("\n");
			}
			test_case++;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}