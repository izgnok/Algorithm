import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();

		int order_list[] = new int[N];
		int num_list[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			num_list[i] = num;
			if (list.isEmpty()) {
				list.add(num);
				order_list[i] = list.size() - 1;
			} else {
				int pre = list.get(list.size() - 1);
				if (pre >= num) {
					int idx = lowerBound(num);
					list.set(idx, num);
					order_list[i] = idx;
				} else {
					list.add(num);
					order_list[i] = list.size() - 1;
				}
			}
		}

		Deque<Integer> stack = new ArrayDeque<>();
		int k = list.size() - 1;
		for (int i = N - 1; i >= 0; i--) {
			if (order_list[i] == k) {
				stack.addLast(num_list[i]);
				k--;
			}
			if(k<0) break;
		}
		sb.append(list.size()).append("\n");
		while (!stack.isEmpty()) {
			sb.append(stack.pollLast()).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int lowerBound(int num) {
		int start = 0;
		int end = list.size() - 1;

		while (start < end) {
			int mid = (start + end) / 2;
			if (list.get(mid) >= num) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return end;
	}

}