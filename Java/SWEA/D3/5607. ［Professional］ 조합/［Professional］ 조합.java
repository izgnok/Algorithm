import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	final static long P = 1234567891;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			long N = Long.parseLong(st.nextToken());
			long K = Long.parseLong(st.nextToken());

			long num1 = factorial(N);
			long num2 = factorial(K) * factorial(N - K) % P;
			long result = num1 * pow(num2, P - 2) % P;

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static long factorial(long N) {
		long fac = 1;

		while (N > 1) {
			fac = (fac * N) % P;
			N--;
		}
		return fac;
	}

	public static long pow(long base, long expo) {

		if (expo == 0) {
			return 1;
		}
		if (expo == 1) {
			return base % P;
		}

		long temp = pow(base, expo / 2);

		if (expo % 2 == 1) {
			return (temp * temp % P) * base % P;
		}
		return temp * temp % P;

	}
}