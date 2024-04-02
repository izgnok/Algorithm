import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int start = 1;
		cnt = new int[10];

		int digit = 1;
		while (start <= N) {
			while (start % 10 != 0 && start <= N) {
				count(start, digit);
				start++;
			}
			while (N % 10 != 9 && start <= N) {
				count(N, digit);
				N--;
			}
			if (start > N)
				break;

			start /= 10;
			N /= 10;

			for (int i = 0; i < 10; i++) {
				cnt[i] += (N - start + 1) * digit;
			}
			digit *= 10;
		}

		for (int i = 0; i < 10; i++) {
			sb.append(cnt[i]).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void count(int num, int digit) {
		while (num > 0) {
			cnt[(num % 10)] += digit;
			num /= 10;
		}
	}
}