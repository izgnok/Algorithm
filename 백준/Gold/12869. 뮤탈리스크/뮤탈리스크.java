import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int list[];

	static Node score;
	static int decrease[][] = { { 9, 3, 1 }, { 9, 1, 3 }, { 3, 9, 1 }, { 3, 1, 9 }, { 1, 3, 9 }, { 1, 9, 3 } };
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		list = new int[3];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list);
		
		result = Integer.MAX_VALUE;
		bfs();
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(list[0], list[1], list[2], 0));
		boolean[][][] visit = new boolean[list[0] + 1][list[1] + 1][list[2] + 1];
		visit[list[0]][list[1]][list[2]] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.a == 0 && node.b == 0 && node.c == 0) {
				if (result > node.count) {
					result = node.count;
				}
				continue;
			}

			for (int i = 0; i < 6; i++) {
				list[0] = Math.max(0, node.a - decrease[i][0]);
				list[1] = Math.max(0, node.b - decrease[i][1]);
				list[2] = Math.max(0, node.c - decrease[i][2]);
				Arrays.sort(list);
				if (visit[list[0]][list[1]][list[2]])
					continue;
				
				visit[list[0]][list[1]][list[2]] = true;
				q.add(new Node(list[0], list[1], list[2], node.count + 1));
			}

		}
	}

	static class Node {
		int a, b, c, count;

		Node(int a, int b, int c, int count) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.count = count;
		}

	}
}