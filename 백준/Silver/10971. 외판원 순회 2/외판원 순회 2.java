import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long result;
	static long map[][];

	static boolean visit[];
	static int path[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		map = new long[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Long.parseLong(st.nextToken());
			}
		}

		visit = new boolean[N + 1];
		path = new int[N];
		result = Long.MAX_VALUE;
		dfs(0, 0, 0);

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, long len, int pre) {
		if (depth == N) {
			if (check(pre, path[0])) {
				len += map[pre][path[0]];
				if (result > len)
					result = len;
			}
			return;
		}
		if (result <= len)
			return;

		for (int i = 1; i <= N; i++) {
			if (visit[i])
				continue;
			// 현재지점에서 i지점이 연결되어있는지 확인
			if (depth == 0 || check(pre, i)) {
				visit[i] = true;
				path[depth] = i;
				dfs(depth + 1, len + map[pre][i], i);
				visit[i] = false;
			}
		}
	}

	static boolean check(int v, int w) {
		if (map[v][w] == 0)
			return false;
		return true;
	}
}