import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long[] A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		A = new long[N];
		B = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Long.parseLong(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Long.parseLong(st.nextToken());
		}

		PriorityQueue<Node> pq1 = new PriorityQueue<>((o1, o2) -> Long.compare(o1.b, o2.b));
		for (int i = 0; i < N; i++) {
			pq1.add(new Node(A[i], B[i]));
		}

		PriorityQueue<Long> pq2 = new PriorityQueue<>((o1, o2) -> Long.compare(o2, o1));
		long result = pq1.poll().a;
		for (int i = 1; i < N - 1; i += 2) {
			pq2.add(pq1.poll().a);
			pq2.add(pq1.poll().a);
			result += pq2.poll();
		} 
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		long a, b;

		Node(long a, long b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public String toString() {
			return "Node [a=" + a + ", b=" + b + "]";
		}
	}
}