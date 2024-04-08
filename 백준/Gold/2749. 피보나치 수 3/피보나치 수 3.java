import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static final int D = 1000000;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());

		long[][] result = fibonacci(N);

		/*
		 * Q^n
		 * 
		 * =
		 * 
		 * { 1, 1 }, ^n { 1, 0 }
		 * 
		 * =
		 * 
		 * { Fn+1, Fn }, { Fn, Fn-1 }
		 * 
		 */
		// System.out.println(result[0][1]);
		System.out.println(result[1][0]);
	}

	public static long[][] fibonacci(long n) {

		// 단위행렬
		long[][] BASE = new long[][] { { 1, 0 }, { 0, 1 } };

		// 피보나치 Q-행렬
		long[][] Q = new long[][] { { 1, 1 }, { 1, 0 } };

		while (n >= 1L) {
			if (n % 2L == 1L) {
				BASE = multiply(BASE, Q);
			}
			Q = multiply(Q, Q);
			n /= 2L;
		}

		return BASE;
	}

	public static long[][] multiply(long[][] matrix1, long[][] matrix2) {

		long[][] result = new long[2][2];

		// 행렬은 ijk, 플로이드워샬은 kij
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					result[i][j] = (result[i][j] + (matrix1[i][k] * matrix2[k][j] % D)) % D;
				}
			}
		}

		return result;
	}
}