import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int[][] leader = new int[100][100];
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					leader[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result =0;
			for(int start=0; start<100; start++) {
				if(leader[0][start] == 0) continue;
				int j = start;
				for(int i=0; i<100; i++) {
					if( j<99 &&leader[i][j+1] == 1) {
						while(leader[i][j+1] == 1) {
							j = j+1;
							if(j==99) break;
						}
					}
					else if( j>0 && leader[i][j-1] == 1) {
						while(leader[i][j-1] ==1) {
							j = j-1;
							if(j==0) break;
						}
					}
					else { // 가로선이 연결되지않은경우
						
					}
				}
				if(leader[99][j] == 2) {
					result = start;
					break;
				}
			}
			sb.append("#" + T + " " + result + "\n");
			if(T==10) break;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}