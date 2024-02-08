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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int test_case = 1;
		while(test_case <= T) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			boolean cmp = false;
			int result = 1;
			for(int i=0; i<str.length(); i++) {
				if(str.charAt(i) == '1' && !cmp) {
					cmp = true;
				}
				if(cmp) {
					if(i == str.length()-1) break;
					if(str.charAt(i) == '0' && str.charAt(i+1) == '1') result++;
					if(str.charAt(i) == '1' && str.charAt(i+1) == '0') result++;
				}
			}
			if(!cmp) result = 0;
			sb.append("#" + test_case + " " + result + "\n" );
			test_case++;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}