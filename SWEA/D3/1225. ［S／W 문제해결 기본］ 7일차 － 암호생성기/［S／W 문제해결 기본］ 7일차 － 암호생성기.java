import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int test_case = Integer.parseInt(st.nextToken());
			Deque<Integer> dq = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<8; i++) {
				dq.addLast(Integer.parseInt(st.nextToken()));
			}
			int k = 1;
			while(true) {
				if(k==6) k = 1;
				int tmp = dq.pollFirst();
				tmp -= k;
				if(tmp<=0) {
					dq.addLast(0);
					break;
				}
				dq.addLast(tmp);
				k++;
			}
			
			sb.append("#" + test_case + " ");
			while(!dq.isEmpty()) {
				sb.append(dq.pollFirst() + " ");
			}
			sb.append("\n");
			if(test_case == 10) break;
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}