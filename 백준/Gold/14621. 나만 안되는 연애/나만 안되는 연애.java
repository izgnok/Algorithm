import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static List<List<Node>> graph;
	static String list[];
	static boolean[] visit;
	static boolean flag;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		list = new String[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			list[i] = st.nextToken();
		}

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, d));
			graph.get(b).add(new Node(a, d));
		}

		visit = new boolean[N + 1];
		flag = false;
		result = 0;
		Prim();
		if (flag)
			sb.append(result);
		else
			sb.append("-1");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void Prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.dist, o2.dist));
		pq.add(new Node(1, 0));

		int cnt = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int x = node.x;
			int dist = node.dist;

			if (visit[x])
				continue;

			result += dist;
			visit[x] = true;
			if (++cnt == N) {
				flag = true;
				break;
			}

			for (int i = 0; i < graph.get(x).size(); i++) {
				Node next = graph.get(x).get(i);
				if (visit[next.x])
					continue;
				if (list[x].equals(list[next.x]))
					continue;
				pq.add(next);
			}
		}
	}

	static class Node {
		int x, dist;

		Node(int x, int dist) {
			this.x = x;
			this.dist = dist;
		}
	}
}