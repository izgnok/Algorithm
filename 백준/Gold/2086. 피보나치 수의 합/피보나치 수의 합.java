import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int D = 1000000000;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		// F(-1)은 존재하지않으니 각각 +2를 해서 구한다.
		long[][] resultA = fibonacci(a + 1);
		long[][] resultB = fibonacci(b + 2);

		long result = (resultB[1][0] - resultA[1][0] + D) % D;
		System.out.println(result);
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