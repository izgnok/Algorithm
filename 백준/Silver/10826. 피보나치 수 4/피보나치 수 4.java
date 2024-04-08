import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		if (N == 0) {
			System.out.println(0);
		}
		else if (N == 1) {
			System.out.println(1);
		}
		else {
			// 1. 동적테이블 생성
			BigInteger[] D = new BigInteger[N + 1];
			
			// 2. 베이스 값 채우기
			D[0] = new BigInteger("0");
			D[1] = new BigInteger("1");

			// 3. 점화식 이용하여 풀기
			for (int i = 2; i < N + 1; i++) {
				D[i] = D[i - 1].add(D[i - 2]);
			}

			System.out.println(D[N]);
		}
	}
}