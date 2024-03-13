import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;

	static List<List<Node>> graph;
	static Loc[] input;
	static double result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		input = new Loc[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double a = Double.parseDouble(st.nextToken());
			double b = Double.parseDouble(st.nextToken());
			input[i] = new Loc(a, b);
		}

		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				Loc a = input[i];
				Loc b = input[j];

				double dist = Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
				graph.get(i).add(new Node(j, dist));
				graph.get(j).add(new Node(i, dist));
			}
		}

		result = 0.0;
		prim();
		sb.append(Math.round(result * 100) / 100.0);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.cost, o2.cost));
		boolean[] visit = new boolean[N];
		pq.add(new Node(0, 0));

		int cnt = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (visit[node.num])
				continue;
			result += node.cost;
			visit[node.num] = true;
			if (++cnt >= N)
				break;

			for (int i = 0; i < graph.get(node.num).size(); i++) {
				Node next = graph.get(node.num).get(i);
				if (visit[next.num])
					continue;
				pq.add(next);
			}
		}
	}

	static class Loc {
		double x, y;

		Loc(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Node {
		int num;
		double cost;

		Node(int num, double cost) {
			this.num = num;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "num: " + num + " cost: " + cost;
		}
	}
}