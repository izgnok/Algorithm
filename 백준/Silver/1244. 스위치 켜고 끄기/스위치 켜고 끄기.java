import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		int[] input = new int[T+1];
		for (int i = 1; i <= T; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());

			int idx = number;
			if (gender == 1) {
				while (idx <= T) {
					if (input[idx] == 0)
						input[idx] = 1;
					else
						input[idx] = 0;
					idx += number;
				}
			} else {
				int cnt = 0;
				while (true) {
					if( idx - cnt < 1 || idx + cnt > T) break;
					if (input[idx - cnt] == input[idx + cnt]) {
						cnt++;
					}
					else break;
				}
				cnt--;
				while(true) {
					if(input[idx-cnt] == 0) input[idx-cnt] = 1;
					else input[idx-cnt] = 0;
					if(cnt==0) break;
					if(input[idx+cnt] == 0) input[idx+cnt] = 1;
					else input[idx+cnt] = 0;
					cnt--;
				}
			}
		}
		
		for(int i=1; i<=T; i++) {
			if(i%20==1 && i!=1) sb.append("\n");
			sb.append(input[i] + " ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}