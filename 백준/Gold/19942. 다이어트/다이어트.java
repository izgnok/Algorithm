import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int mp, mf, ms, mv;
	static Node[] list;
	static boolean[] visit;
	static int result;
	static List<Integer> rs_list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());

		list = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[i] = new Node(p, f, s, v, c);
		}

		visit = new boolean[N + 1];
		result = Integer.MAX_VALUE;
		dfs(1);
		if(result == Integer.MAX_VALUE) result = -1;
		sb.append(result).append("\n");
		for (int i = 0; i < rs_list.size(); i++) {
			sb.append(rs_list.get(i)).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth) {
		if (depth == N + 1) {
			Calc();
			return;
		}

		visit[depth] = true;
		dfs(depth + 1);
		visit[depth] = false;
		dfs(depth + 1);
	}

	static void Calc() {
		List<Integer> tmp = new ArrayList<>();
		int sp = 0, sf = 0, ss = 0, sv = 0, sc = 0;
		for (int i = 1; i <= N; i++) {
			if (visit[i]) {
				Node node = list[i];
				sp += node.p;
				sf += node.f;
				ss += node.s;
				sv += node.v;
				sc += node.c;
				tmp.add(i);
			}
		}

		if (sp >= mp && sf >= mf && ss >= ms && sv >= mv) {
			if (result > sc) {
				result = sc;
				rs_list = tmp;
			} else if (result == sc) {
				int rs_idx = 0;
				int tmp_idx = 0;

				boolean flag = false;
				while (rs_idx < rs_list.size() && tmp_idx < tmp.size()) {
					int r = rs_list.get(rs_idx);
					int t = tmp.get(tmp_idx);

					if (r > t) {
						rs_list = tmp;
						flag = true;
						break;
					} else if (r < t) {
						flag = true;
						break;
					}
					rs_idx++;
					tmp_idx++;
				}
				if (!flag) {
					if (rs_list.size() > tmp.size()) {
						rs_list = tmp;
					}
				}
			}
		}
	}

	static class Node {
		int p, f, s, v, c;

		public Node(int p, int f, int s, int v, int c) {
			this.p = p;
			this.f = f;
			this.s = s;
			this.v = v;
			this.c = c;
		}
	}
}