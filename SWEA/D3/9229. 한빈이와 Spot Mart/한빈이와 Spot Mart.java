import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int test_case = 1;
		while (test_case <= T) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] input = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}

			int cnt = 0;
			int start = 0;
			int end = N - 1;

			Arrays.sort(input);

			int result = -1;
			while (start != end) {
				int cur = input[start] + input[end];
				if (cur > M)
					end--;
				else {
					if (result < cur)
						result = cur;
					start++;
				}
			}
			sb.append("#" + test_case + " " + result + "\n");
			test_case++;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}