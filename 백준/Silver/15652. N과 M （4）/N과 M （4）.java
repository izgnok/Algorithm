import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	// static boolean[] visit;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// visit = new boolean[N + 1];
		dfs(0, new int[M]);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int[] result) {
		if (depth == M) {
			for (int r : result) {
				sb.append(r + " ");
			}
			sb.append("\n");
			return;
		}

		int[] tmp = result.clone();

		for (int i = 1; i <= N; i++) {
			if (depth != 0 && tmp[depth - 1] <= i) {
				tmp[depth] = i;
				dfs(depth + 1, tmp);
			}
			else if( depth == 0){
				tmp[depth] = i;
				dfs(depth + 1, tmp);
			}
		}
	}
}