import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static Node home, company;
	static Node[] job;
	static int N;
	static int result;
	static int[] order;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int test_case = 0;
		while (test_case++ < T) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			job = new Node[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 2; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if (i == 0) {
					company = new Node(x, y);
				} else {
					home = new Node(x, y);
				}
			}

			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				job[i] = new Node(x, y);
			}

			order = new int[N];
			visit = new boolean[N];
			result = Integer.MAX_VALUE;
			dfs(0);
			
			sb.append("#" + test_case + " " + result + "\n");

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void dfs(int depth) { // 순서뽑기
		if (depth == N) {
			dist(); // 거리구하기
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			order[depth] = i;
			dfs(depth + 1);
			visit[i] = false;
		}
	}

	static void dist() {
		int x = company.x;
		int y = company.y;
		int cur = 0;

		for (int i = 0; i < N; i++) {
			int k = order[i];
			Node node = job[k];
			cur += Math.abs(x - node.x) + Math.abs(y - node.y);
			x = node.x;
			y = node.y;
		}

		int home_x = home.x;
		int home_y = home.y;
		cur += Math.abs(x - home_x) + Math.abs(y - home_y);

		if (result > cur)
			result = cur;
	}
}