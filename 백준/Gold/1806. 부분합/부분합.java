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

public class Main {

	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int sum = 0;
		int result = Integer.MAX_VALUE;

		Queue<Integer> q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			sum += num;
			q.add(num);

			if (sum >= M) {
				while (sum >= M) {
					sum -= q.poll();
				}
				int len = q.size() + 1;
				if (result > len)
					result = len;
			}
		}
		if (result == Integer.MAX_VALUE)
			result = 0;
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}