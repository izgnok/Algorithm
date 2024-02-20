import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N, K;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			String str = br.readLine();

			Deque<Character> deque = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				deque.addLast(str.charAt(i));
			}

			TreeSet<Integer> tree = new TreeSet<>();

			for (int i = 0; i < N / 4; i++) {
				int asds = 0;
				Iterator<Character> iter = deque.iterator();

				while (iter.hasNext()) {
					String tmp = null;
					for (int j = N / 4; j > 0; j--) {
						char a = (char) iter.next();
						if (tmp == null)
							tmp = a + "";
						else
							tmp += a;
					}
					tree.add(Integer.parseInt(tmp, 16));
				}
				deque.addFirst(deque.pollLast());
			}

			Iterator<Integer> iter = tree.descendingIterator();

			sb.append("#").append(test_case).append(" ");
			for (int i = 0; i < K; i++) {
				int result =iter.next();
				if (i == K - 1)
					sb.append(result).append("\n");
			}

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}