import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
	static BufferedReader br;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			bw.write(result() + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static String result() throws Exception {
		return ckMinusCycle() ? "YES" : "NO";
	}

	public static boolean ckMinusCycle() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 지점의 수
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수
		int W = Integer.parseInt(st.nextToken()); // 웜홀의 개수
		ArrayList<Edge> edge = new ArrayList<>();
		for (int i = 0; i < M + W; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			if (i >= M) {
				edge.add(new Edge(S, E, -T));
			} else {
				edge.add(new Edge(S, E, T));
				edge.add(new Edge(E, S, T));
			}
		}
		int[] times = new int[N + 1];
		boolean ck = false;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < edge.size(); j++) {
				Edge cur = edge.get(j);
				int time = times[cur.from] + cur.time;
				if (times[cur.to] > time) {
					times[cur.to] = time;
					if (i == N) {
						ck = true;
					}
				}
			}
		}
		return ck;
	}
}

class Edge {
	int from;
	int to;
	int time;

	Edge(int from, int to, int time) {
		this.from = from;
		this.to = to;
		this.time = time;
	}
}