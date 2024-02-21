import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		makeSet();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (k == 0) {
				union(a, b);
			} else {
				if (find(a) == find(b))
					sb.append("YES").append("\n");
				else
					sb.append("NO").append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		return;
	}

	static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		if (find(a) == find(b)) {
			return false;
		}
		parents[find(b)] = a;
		return true;
	}

}