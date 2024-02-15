import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Deque<Node> dq = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			while (!dq.isEmpty() && dq.peekLast().num > num)
				dq.pollLast();
			dq.addLast(new Node(i, num));
			if (dq.peekFirst().idx < i - L + 1) {
				dq.pollFirst();
			}
			sb.append(dq.peekFirst().num + " ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		int idx, num;

		Node(int idx, int num) {
			this.idx = idx;
			this.num = num;
		}
	}
}