import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static String[] input;
	static int N;
	static int Result = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		String str = new StringTokenizer(br.readLine()).nextToken();
		input = new String[N];
		input = str.split("");

		for (int i = 2; i < N; i += 2) {
			int cur = Integer.parseInt(input[0]);
			for (int j = 1; j <i-4 ; j += 2) {
				if (input[j].equals("+")) {
					cur += Integer.parseInt(input[j + 1]);
				} else if (input[j].equals("-")) {
					cur -= Integer.parseInt(input[j + 1]);
				} else {
					cur *= Integer.parseInt(input[j + 1]);
				}
			}
			if( i!= 2 ) dfs(i, cur, input[i - 1], input[i-3]);
			else dfs(i, 0, input[i - 1], "");
		}
		
		int direct = Integer.parseInt(input[0]);
		for(int i=1; i<N; i+=2) {
			if (input[i].equals("+")) {
				direct += Integer.parseInt(input[i + 1]);
			} else if (input[i].equals("-")) {
				direct -= Integer.parseInt(input[i + 1]);
			} else {
				direct *= Integer.parseInt(input[i + 1]);
			}
		}
		if(Result < direct) Result = direct;

		sb.append(Result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int idx, int cur, String oper, String lastoper) {
		
		int tmp;
		if (oper.equals("+")) {
			tmp = Integer.parseInt(input[idx - 2]) + Integer.parseInt(input[idx]);
		} else if (oper.equals("-")) {
			tmp = Integer.parseInt(input[idx - 2]) - Integer.parseInt(input[idx]);
		} else {
			tmp = Integer.parseInt(input[idx - 2]) * Integer.parseInt(input[idx]);
		}
		if (lastoper.equals("")) {

		} else if (lastoper.equals("+")) {
			tmp = tmp + cur;
		} else if (lastoper.equals("-")) {
			tmp = cur - tmp;
		} else {
			tmp = tmp * cur;
		}
		if(idx == N-1) {
			if(Result < tmp) Result = tmp;
			return;
		}
		
		for (int i = idx + 4; i < N; i += 2) {
			int t = tmp;
			for (int j = idx + 1; j < i - 4; j += 2) {
				if (input[j].equals("+")) {
					t += Integer.parseInt(input[j + 1]);
				} else if (input[j].equals("-")) {
					t -= Integer.parseInt(input[j + 1]);
				} else {
					t *= Integer.parseInt(input[j + 1]);
				}
			}
			dfs(i, t, input[i - 1], input[i - 3]);
		}
		
		int k = tmp;
		for(int i = idx+1; i<N; i+=2) {
			if (input[i].equals("+")) {
				k += Integer.parseInt(input[i + 1]);
			} else if (input[i].equals("-")) {
				k -= Integer.parseInt(input[i + 1]);
			} else {
				k *= Integer.parseInt(input[i + 1]);
			}
		}
		if(Result < k) Result = k;
		
	}
}