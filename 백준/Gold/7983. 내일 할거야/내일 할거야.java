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

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		Node[] arr = new Node[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int work = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int start = end - work + 1;
			arr[i] = new Node(start, end, work);
		}
		Arrays.sort(arr);

		int min = arr[0].start;
		int max = arr[0].end;
		int all_work = arr[0].work;

		for (int i = 1; i < N; i++) {
			int start = arr[i].start;
			int end = arr[i].end;

			if (end >= min) {
				all_work += arr[i].work;
				min = max - all_work + 1;
			}
			else {
				max = end;
				min = start;
				all_work = arr[i].work;
			}
		}
		int result = min - 1;
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node implements Comparable<Node> {
		int start, end, work;

		Node(int start, int end, int work) {
			this.start = start;
			this.end = end;
			this.work = work;
		}

		@Override
		public int compareTo(Node o) {
			if(this.end == o.end) {
				return Integer.compare(this.start, o.start);
			}
			return Integer.compare(o.end, this.end);
		}
	}
}