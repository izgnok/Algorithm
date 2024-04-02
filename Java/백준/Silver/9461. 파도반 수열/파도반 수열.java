import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static long[] P;
	static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		// 1. 동적테이블 생성
		P = new long[101];

		// 2. 베이스 값 채우기
		P[1] = 1L;
		P[2] = 1L;
		P[3] = 1L;
		P[4] = 2L;
		P[5] = 2L;

		// 3. 점화식 세우기
		for (int i = 6; i < 101; i++) {
			P[i] = P[i - 5] + P[i - 1];
		}

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			System.out.println(P[N]);
		}
	}
}