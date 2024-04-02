import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static final int D = 998244353;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			long N = Long.parseLong(br.readLine());
			long[][] base = padovan(N);

			/*
			 * Q^n
			 * 
			 * =
			 * 
			 * { 0, 1, 0 }, ^n
			 * { 0, 0, 1 },
			 * { 1, 1, 0 }
			 * 
			 * =
			 * 
			 * { Pn-4, Pn-2, Pn-3 },
			 * { Pn-3, Pn-1, Pn-2 },
			 * { Pn-2, Pn, Pn-1 }
			 * 
			 */
			System.out.println(base[2][1]);
		}
	}

	public static long[][] padovan(long n) {

		// 단위행렬
		long[][] BASE = new long[][] {
			{ 1, 0, 0 },
			{ 0, 1, 0 },
			{ 0, 0, 1 }
		};

		// 파도반 Q-행렬
		long[][] Q = new long[][] {
			{ 0, 1, 0 },
			{ 0, 0, 1 },
			{ 1, 1, 0 }
		};

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

		long[][] result = new long[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					result[i][j] = (result[i][j] + (matrix1[i][k] * matrix2[k][j] % D)) % D;
				}
			}
		}

		return result;
	}
}