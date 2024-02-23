import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, C, result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		Queue<Integer> input = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			input.add(Integer.parseInt(st.nextToken()));
		}

		Queue<Integer> q = new ArrayDeque<>();
		HashMap<Integer, Integer> type = new HashMap<>();

		for (int i = 0; i < K; i++) {
			int num = input.poll();
			if (!type.containsKey(num)) { // 같은 종류가 존재하지 않음 경우
				type.put(num, 1);
			} else { // 같은 종류가 있음
				type.put(num, type.get(num) + 1);
			}
			input.add(num);
			q.add(num);
		}
		result = type.size();
		if (!type.containsKey(C))
			result++;

		for (int i = 0; i < N; i++) { // 최대 N번 반복
			if (result == K + 1) // 최대치도달하면 종료
				break;
			
			int pre = q.poll();
			int next = input.poll();

			if (type.get(pre) == 1) { // 같은 종류가 한개라면 제거
				type.remove(pre);
			} else {
				type.put(pre, type.get(pre) - 1); // 같은 종류가 한개를 넘으면 개수 감소
			}

			if (!type.containsKey(next)) { // 같은 종류가 존재하지 않음 경우
				type.put(next, 1);
			} else { // 같은 종류가 있음
				type.put(next, type.get(next) + 1);
			}

			if (result < type.size()) { // 종류가 더 많은 경우
				result = type.size();
			} else if (result == type.size() && !type.containsKey(C)) { // 종류개수는 같지만 쿠폰을 포함하지않은 경우
				result++;
			}
			q.add(next);
			input.add(next);
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}