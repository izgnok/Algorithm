import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		Deque<Work> stack = new ArrayDeque<>();
		Work cur = null;
		int result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			if (type == 0) {
				if (cur != null) {
					cur.time--;
				}
			} else {
				int score = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken()) - 1;
				if (cur != null) {
					stack.addLast(cur);
				}
				cur = new Work(score, time);
			}

			if (cur != null && cur.time == 0) {
				result += cur.score;
				if (!stack.isEmpty()) {
					cur = stack.pollLast();
				} else
					cur = null;
			}

		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Work {
		int score, time;

		Work(int score, int time) {
			this.score = score;
			this.time = time;
		}
	}
}