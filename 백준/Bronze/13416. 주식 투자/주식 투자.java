import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
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

		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int T = Integer.parseInt(st.nextToken());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());

			List<PriorityQueue<Integer>> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				list.add(new PriorityQueue<>((o1, o2) -> o2 - o1));
			}

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < 3; j++) {
					list.get(i).add(Integer.parseInt(st.nextToken()));
				}
			}

			int result = 0;
			for (int i = 0; i < N; i++) {
				int num = list.get(i).poll();
				if (num > 0) {
					result += num;
				}
			}
			sb.append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}