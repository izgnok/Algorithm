import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long S;
	static long arr1[], arr2[];
	static List<Long> list1;
	static List<Long> list2;
	static long result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Long.parseLong(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr1 = new long[N / 2];
		for (int i = 0; i < N / 2; i++) {
			arr1[i] = Long.parseLong(st.nextToken());
		}
		arr2 = new long[N - N / 2];
		int idx = 0;
		for (int i = N / 2; i < N; i++) {
			arr2[idx++] = Long.parseLong(st.nextToken());
		}

		list1 = new ArrayList<>();
		dfs(arr1, 0, N / 2, 0, list1);
		list2 = new ArrayList<>();
		dfs(arr2, 0, N - N / 2, 0, list2);

		Collections.sort(list1, (o1,o2) -> Long.compare(o1, o2));
		Collections.sort(list2, (o1,o2) -> Long.compare(o2, o1));

		long idx1 = 0;
		long idx2 = 0;
		while (idx1 < list1.size() && idx2 < list2.size()) {
			long a = list1.get((int) idx1);
			long b = list2.get((int) idx2);
			long tmp = a + b;

			if (tmp < S) {
				idx1++;
			} else if (tmp > S) {
				idx2++;
			} else {
				long t1 = idx1;
				long t2 = idx2;

				while (t1 < list1.size() && list1.get((int) t1) == a) {
					t1++;
				}
				while (t2 < list2.size() && list2.get((int) t2) == b) {
					t2++;
				}
				result += (t1 - idx1) * (t2 - idx2);
				idx1 = t1;
				idx2 = t2;
			}
		}

		if (S == 0) {
			result--;
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(long arr[], int depth, int N, long sum, List<Long> list) { // 부분집합
		if (depth == N) {
			list.add(sum);
			return;
		}

		sum += arr[depth];
		dfs(arr, depth + 1, N, sum, list);
		sum -= arr[depth];
		dfs(arr, depth + 1, N, sum, list);
	}

}