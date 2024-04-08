import java.util.HashMap;
import java.util.Scanner;

public class Solution {

	static HashMap<Long, Long> fMap;  // 동적테이블

	public static void main(String[] args) {

		// 1. 동적테이블 생성
		fMap = new HashMap<>();

		// 2. 베이스 값 채우기
		// F(9), F(99), F(999) ... F(9999999999999999)까지를 미리 구한다.
		for (long i = 1L; i < 17L; i++) {

			// 9, 99, 999 ... 9999999999999999
			long num = pow10(i) - 1;

			// f(9) 20f(9) 300f(9) 4000f(9)
			long length = String.valueOf(num).length();
			long result = length * pow10(i) / 10L * 45L /* f(9) */;
			fMap.put(num, result);
		}

		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			long A = scanner.nextLong();
			long B = scanner.nextLong();
			long result = sum(A, B);
			System.out.println("#" + test_case + " " + result);
		}
	}

	// 밑이 10이고 지수가 num인 값 구하기
	public static long pow10(long num) {
		return (long) Math.pow(10, num);
	}

	// 구간 start ~ end의 구간 합 구하기
	// 구간 합은 그 구간에 포함되는 모든 정수의 각 자리를 합한 값
	public static long sum(long start, long end) {

		// 시작 수가 0 또는 1이면 끝 수까지 더한다.
		if (start == 0 || start == 1) {
			return f(end);
		}
		// 시작 수와 끝 수가 같으면 끝 수까지의 합에서 끝 앞의 수까지의 합을 뺀다.
		else if (start == end) {
			return f(end) - f(end - 1);
		}
		// 그 외
		else {
			return f(end) - f(start - 1);
		}
	}

	public static long f(long n) {

		// 이미 계산된 F(n)이 있다면 그대로 사용
		if (fMap.containsKey(n)) {
			return fMap.get(n);
		}
		// n이 9이하의 수이면 1부터 n까지의 합을 구한다.
		else if (n <= 9) {
			return n * (n + 1L) / 2L;
		}
		// 증명에서 구한 수식을 사용한다.
		else {
			long length = String.valueOf(n).length() - 1;
			long v = pow10(length);
			
			// n이 10번대이면 f(9) 사용
			// n이 20번대이면 f(19) 사용
			// n이 30번대이면 f(29) 사용
			fMap.put(n, f(n - 1L - n % v) + g(n));
			return fMap.get(n);
		}
	}

	public static long g(long n) {
		// n이 9이하의 수이면 1부터 n까지의 합을 구한다.
		if (n <= 9) {
			return n * (n + 1L) / 2L;
		}
		// 증명에서 구한 수식을 사용한다.
		else {
			long length = String.valueOf(n).length() - 1;
			long v = pow10(length);
			
			// n이 10번대이면 십의자리합 + 일의자리합
			// 일의자리합은 다시 f로 구한다.
			// 10: 1 * 1 + f(0)
			// 11: 1 * 2 + f(1)
			// 12: 1 * 3 + f(2)
			// ...
			// 19: 1 * 10 + f(9)
			
			// n이 20번대이면
			// 20: 2 * 1 + f(0)
			// 21: 2 * 2 + f(1)
			// 22: 2 * 3 + f(2)
			// ...
			// 29: 2 * 10 + f(9)
			return (n / v) * (n % v + 1L) + f(n % v);
		}
	}
}