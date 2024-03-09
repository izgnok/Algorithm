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
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Node[] arr;
	static List<Node> list;
	static int size;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		arr = new Node[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = new Node(x, y);
		}
		Arrays.sort(arr);

		list = new ArrayList<>();
		int order[] = new int[N];

		list.add(arr[0]);
		order[0] = 0;

		for (int i = 1; i < N; i++) {
			int num = list.get(list.size()-1).y;
			int next = arr[i].y;

			if (num < next) {
				list.add(arr[i]);
				order[i] = list.size() - 1;
			} else {
				int idx = lowerBound(next);
				list.set(idx, arr[i]);
				order[i] = idx;
			}
		}

		List<Integer> result = new ArrayList<>();
		int k = list.size() - 1;
		boolean check[] = new boolean[N];
		for (int i = N - 1; i >= 0; i--) {
			if (order[i] == k) {
				check[i] = true;
				k--;
			} 
			if(k<0) break;
		}
		
		for(int i=0; i<N; i++) {
			if(!check[i]) {
				result.add(arr[i].x);
			}
		}
		
//		Collections.sort(result);
		sb.append(result.size()).append("\n");
		for(int i=0; i<result.size();i++) {
			sb.append(result.get(i)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int lowerBound(int num) {

		int start = 0;
		int end = list.size()-1;
		while (start < end) {
			int mid = (start + end) / 2;
			int pre = list.get(mid).y;

			if (num > pre) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return end;
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.x, o.x);
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "A: " + x + " B: " + y;
		}
	}
}