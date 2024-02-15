import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1074
public class Main {

	private static int answer;

	// 결과를 한 번에 출력하기 위한 StringBuilder
	private static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		//System.setIn(new FileInputStream("res/1074_input1.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * 1. 입력 파일 객체화
		 */
		String[] split = in.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int r = Integer.parseInt(split[1]);
		int c = Integer.parseInt(split[2]);
		answer = 0;

		/**
		 * 2. 알고리즘 풀기
		 */
		int size = power(2, N);  // 한 변의 길이

		cut(r, c, size);

		/**
		 * 3. 정답 출력
		 */
		sb.append(answer).append("\n");
		System.out.println(sb);
	}

	/*private static int power(int N) {

		int result = 1;
		for (int i = 0; i < N; i++) {
			result *= 2;
		}

		return result;
	}*/

	private static int power(int x, int n) {

		if (n == 1) {
			return x;
		}

		int y = power(x, n / 2);

		// 지수가 홀수일때 한번 더 곱해줌.
		return  (n % 2 == 0) ? y * y : y * y * x;
	}

	private static void cut(int row, int col, int size) {

		// 기저 부분 (종료 조건)
		if (size == 1) {
			return;
		}

		// 유도 부분
		int half = size / 2;

		if (row < half && col < half) {
			cut(row, col, half);  // 좌상
		}
		else if (row < half && half <= col) {
			answer += size * size / 4;
			cut(row, col - half, half);  //우상
		}
		else if (half <= row && col < half) {
			answer += (size * size / 4) * 2;
			cut(row - half, col, half);  //좌하
		}
		else if (half <= row && half <= col) {
			answer += (size * size / 4) * 3;
			cut(row - half, col - half, half);  //우하
		}
	}
}