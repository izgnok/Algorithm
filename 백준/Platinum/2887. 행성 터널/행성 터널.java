import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Loc[] input;
	static List<List<Node>> graph;
	static long result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		input = new Loc[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			input[i] = new Loc(x, y, z, i);
		}
		
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}

		// x기준으로 간선 추가
		Arrays.sort(input, (o1, o2) -> Integer.compare(o1.x, o2.x));
		for (int i = 0; i < N - 1; i++) {
			int dist = Math.abs(input[i].x - input[i + 1].x);
			int a = input[i].num;
			int b = input[i + 1].num;
			graph.get(a).add(new Node(b, dist));
			graph.get(b).add(new Node(a, dist));
		}

		// y기준으로 간선 추가
		Arrays.sort(input, (o1, o2) -> Integer.compare(o1.y, o2.y));
		for (int i = 0; i < N - 1; i++) {
			int dist = Math.abs(input[i].y - input[i + 1].y);
			int a = input[i].num;
			int b = input[i + 1].num;
			graph.get(a).add(new Node(b, dist));
			graph.get(b).add(new Node(a, dist));
		}

		// z기준으로 간선 추가
		Arrays.sort(input, (o1, o2) -> Integer.compare(o1.z, o2.z));
		for (int i = 0; i < N - 1; i++) {
			int dist = Math.abs(input[i].z - input[i + 1].z);
			int a = input[i].num;
			int b = input[i + 1].num;
			graph.get(a).add(new Node(b, dist));
			graph.get(b).add(new Node(a, dist));
		}

		result = 0;
		prim();

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.cost, o2.cost));
		boolean visit[] = new boolean[N];
		pq.add(new Node(0, 0));
		int cnt = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int num = node.num;
			long cost = node.cost;
			if (visit[num])
				continue;
			result += cost;
			visit[num] = true;
			if (++cnt == N)
				return;

			for (int i = 0; i < graph.get(num).size(); i++) {
				Node next = graph.get(num).get(i);
				if (visit[next.num])
					continue;
				pq.add(next);
			}

		}
	}

	static class Loc {
		int x, y, z, num;

		Loc(int x, int y, int z, int num) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.num = num;
		}

		@Override
		public String toString() {
			return "x: " + x + " y: " + y + " z: " + z + " num: " + num;
		}
	}

	static class Node {
		int num;
		long cost;

		Node(int num, long cost) {
			this.num = num;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "num: " + num + " cost: " + cost;
		}
	}
}