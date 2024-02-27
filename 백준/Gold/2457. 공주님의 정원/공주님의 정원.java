import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Node[] schedule;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		schedule = new Node[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			a = a * 100;
			int b = Integer.parseInt(st.nextToken());
			a = a + b;

			int c = Integer.parseInt(st.nextToken());
			c = c * 100;
			int d = Integer.parseInt(st.nextToken());
			c = c + d;

			schedule[i] = new Node(a, c);
		}
		Arrays.sort(schedule);

		Node work = null;
		result = 0;
		int idx = -1;
		for (int i = 0; i < N; i++) {
			Node first = schedule[i];
			if (first.end > 1130 && first.start <= 1130) {
				if (work == null) {
					work = first;
					idx = i;
					result = 1;
				} else {
					if (work.start > first.start) {
						work = first;
						idx = i;
					}
				}
			}
		}

		if (work != null) {
			for (int i = idx + 1; i < N; i++) {
				if (work.start <= 301)
					break;
				Node cur = new Node(work.start, work.end);
				for (int j = i; j < N; j++) {
					Node next = schedule[j];
					if (cur.start <= next.end && work.start > next.start) {
						work.start = next.start;
						i = j;
					}
				}
				if (cur.start != work.start) {
					result++;
				} else {
					break;
				}
			}
		}

		if (work != null && work.start > 301)
			result = 0;

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node implements Comparable<Node> {
		int start, end;

		Node(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Node o) {
			if (o.end == this.end) {
				return this.start - o.start;
			}
			return o.end - this.end;
		}
		
		@Override
		public String toString() {
			return "start: " + this.start + " end: " + this.end;
		}
	}
}