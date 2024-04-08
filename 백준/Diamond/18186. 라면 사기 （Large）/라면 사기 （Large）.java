import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] list;
	static long result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());
		list = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}

		if (B < C) {
			for (int i = 0; i < N; i++) {
				result += list[i] * B;
			}
		} else {
			for (int i = 0; i < N; i++) {
				if (i < N - 2) {
					if (list[i + 1] > list[i + 2]) {
						int tmp = Math.min(list[i], list[i + 1] - list[i + 2]);
						result += (B+C) * tmp;
						list[i] -= tmp;
						list[i + 1] -= tmp;

						tmp = Math.min(Math.min(list[i], list[i + 1]), list[i + 2]);
						result += (B+2*C) * tmp;
						list[i] -= tmp;
						list[i + 1] -= tmp;
						list[i + 2] -= tmp;
					} else {
						int tmp = Math.min(Math.min(list[i], list[i + 1]), list[i + 2]);
						result += (B+2*C) * tmp;
						list[i] -= tmp;
						list[i + 1] -= tmp;
						list[i + 2] -= tmp;

						tmp = Math.min(list[i], list[i + 1]);
						result += (B+C) * tmp;
						list[i] -= tmp;
						list[i + 1] -= tmp;
					}
				} else if (i < N - 1) {
					int tmp = Math.min(list[i], list[i + 1]);
					result += (B+C) * tmp;
					list[i] -= tmp;
					list[i + 1] -= tmp;
				}
				result += B * list[i];
			}
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}