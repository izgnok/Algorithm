import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
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

		List<Integer> minus = new ArrayList<>();
		List<Integer> plus = new ArrayList<>();
		int zero = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());

			if (num > 0) {
				plus.add(num);
			} else if (num < 0) {
				minus.add(num);
			} else {
				zero++;
			}
		}

		Collections.sort(plus);
		Collections.sort(minus);

		long result = 0;
		for (int i = plus.size() - 1; i >= 0; i--) {
			if (plus.get(i) == 1)
				result += 1;
			else if (i - 1 >= 0 && plus.get(i - 1) != 1) {
				result += plus.get(i) * plus.get(i - 1);
				i--;
			} else {
				result += plus.get(i);
			}
		}
		
		if (minus.size() % 2 == 0) {
			for (int i = 0; i < minus.size(); i += 2) {
				result += minus.get(i) * minus.get(i + 1);
			}
		} else {
			for (int i = 0; i < minus.size(); i++) {
				if(i+1 < minus.size()) {
					result += minus.get(i) * minus.get(i+1);
					i++;
				}
				else {
					if(zero == 0) {
						result += minus.get(i);
					}
				}
			}
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}