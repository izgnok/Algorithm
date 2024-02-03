import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int Max = Integer.MIN_VALUE;
	static int Min = Integer.MAX_VALUE;
	static int input[];
	static int oper[];
	static boolean visit[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		input = new int[N];
		oper = new int[N - 1];
		visit = new boolean[N - 1];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int idx = 0;
		int range = Integer.parseInt(st.nextToken());
		for (int i = 0; i < range; i++) { // + , - , * , /
			oper[idx++] = 1;
		}
		range = Integer.parseInt(st.nextToken());
		for (int i = 0; i < range; i++) {
			oper[idx++] = 2;
		}
		range = Integer.parseInt(st.nextToken());
		for (int i = 0; i < range; i++) {
			oper[idx++] = 3;
		}
		range = Integer.parseInt(st.nextToken());
		for (int i = 0; i < range; i++) {
			oper[idx++] = 4;
		}

		dfs(1, input[0]);

		sb.append(Max).append("\n").append(Min);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int cur) {
		if (depth == N) {
			if (Max < cur)
				Max = cur;
			if (Min > cur)
				Min = cur;
			return;
		}
		for (int i = 0; i < N-1; i++) {
			int tmp;
			if(visit[i]) continue;
			if (oper[i] == 1) {
				tmp = cur + input[depth];
			} else if (oper[i] == 2) {
				tmp = cur - input[depth];
			} else if (oper[i] == 3) {
				tmp = cur * input[depth];
			} else {
				if(input[depth] == 0) return;
				if (cur < 0) {
					tmp = cur * -1;
					tmp = tmp /input[depth];
					tmp *= -1;
				} else
					tmp = cur / input[depth];
			}
			visit[i] = true;
			dfs(depth + 1, tmp);
			visit[i] = false;
		}
	}
}