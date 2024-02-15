import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<List<Node>> graph = new ArrayList<>();
	static long result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, w));
			graph.get(b).add(new Node(a, w));
		}
		
		prim();
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> (int) (o1.cost - o2.cost));
		boolean[] visit = new boolean[N + 1];
		pq.add(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int x = node.x;
			if(!visit[x]) result += node.cost;
			visit[x] = true;

			for (int i = 0; i < graph.get(x).size(); i++) {
				Node next = graph.get(x).get(i);
				if (visit[next.x])
					continue;
				pq.add(new Node(next.x, next.cost));
			}

		}
	}

	static class Node {
		int x;
		long cost;

		Node(int x, long cost) {
			this.x = x;
			this.cost = cost;
		}
	}

}