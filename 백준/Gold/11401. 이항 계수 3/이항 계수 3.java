import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	final static long P = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());

		long numer = factorial(N);
		long denom = factorial(K) * factorial(N - K) % P;
		long result = numer * pow(denom, P - 2) % P;

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static long factorial(long N) {
		long fac = 1L;

		while (N > 1) {
			fac = (fac * N) % P;
			N--;
		}
		return fac;
	}

	public static long pow(long base, long expo) {
		// 지수가 1일 경우 base^1 이므로 base % P를 리턴
		if (expo == 1) {
			return base % P;
		}

		// 지수의 절반에 해당하는 base^(expo / 2) 을 구한다.
		long temp = pow(base, expo / 2);

		/*
		 * 현재 지수가 홀 수 였다면 base^(expo / 2) * base^(expo / 2) * base 이므로 base를 한 번 더 곱해주어야
		 * 한다.
		 * 
		 * ex) A^9 = A^4 * A^4 * A
		 */
		if (expo % 2 == 1) {
			return (temp * temp % P) * base % P;
		}
		return temp * temp % P;

	}
}