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
		
		int[] input = new int[N+1];
		input[0] = 0;
		for(int i=1; i<=N ; i++) {
			st = new StringTokenizer(br.readLine());
			input[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(input);
		long result = 0;
		
		int max = 1;
		for(int i=1; i<=N ;i++) {
			if(input[i] >= max) {
				result += input[i] - max;
				max++;
			}
		}

		sb.append(String.valueOf(result));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}