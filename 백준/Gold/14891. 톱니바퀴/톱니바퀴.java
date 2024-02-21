import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;

	static List<ArrayDeque<Integer>> list;

	static int[] A = new int[8];
	static int[] B = new int[8];
	static int result;
	static int[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;

		list = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			list.add(new ArrayDeque<>());
		}

		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				int num = str.charAt(j) - '0';
				list.get(i).addLast(num);
			}
		}

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		result = 0;
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			visit = new int[4];
			same(idx - 1, dir);
			rotate();
		}
		sum();
		sb.append(result);
		// sb.append("#").append(test_case).append(" ").append(result).append("\n");

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void rotate() { // 회전

		for (int i = 0; i < 4; i++) {
			if (visit[i] == 0)
				continue;
			if (visit[i] == 1) {
				list.get(i).addFirst(list.get(i).pollLast());
			} else {
				list.get(i).addLast(list.get(i).pollFirst());
			}
		}
	}

	static void same(int idx, int dir) { // 같은지 확인

		visit[idx] = dir;
		for (int i = idx; i > 0; i--) {

			Deque dq = list.get(i - 1);
			Deque dq2 = list.get(i);

			for (int k = 0; k < 8; k++) {
				A[k] = (int) dq.pollFirst();
				B[k] = (int) dq2.pollFirst();
			}
			for (int k = 0; k < 8; k++) {
				dq.addLast(A[k]);
				dq2.addLast(B[k]);
			}
			if (A[2] != B[6]) {
				visit[i - 1] = visit[i] * (-1);
			} else {
				visit[i - 1] = 0;
				break;
			}
		}

		for (int i = idx; i < 3; i++) {

			Deque<Integer> dq = list.get(i);
			Deque<Integer> dq2 = list.get(i + 1);

			for (int k = 0; k < 8; k++) {
				A[k] = (int) dq.poll();
				B[k] = (int) dq2.poll();
			}
			for (int k = 0; k < 8; k++) {
				dq.addLast(A[k]);
				dq2.addLast(B[k]);
			}
			if (A[2] != B[6]) {
				visit[i + 1] = visit[i] * (-1);
			} else {
				visit[i + 1] = 0;
				break;
			}
		}
	}

	static void sum() { // 합계구하기
		for (int i = 0; i < 4; i++) {
			Deque<Integer> dq = list.get(i);

			if ((int) dq.peek() == 1) {
				result += Math.pow(2, i);
			}
		}
	}

}