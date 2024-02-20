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
	static int Day, Month, Month_3, Year; // 이용권 가격
	static int use[] = new int[12]; // 매달 이용일
	static int ticket[] = new int[12];

	static int result; // 최소 결과값

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			Day = Integer.parseInt(st.nextToken());
			Month = Integer.parseInt(st.nextToken());
			Month_3 = Integer.parseInt(st.nextToken());
			Year = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				use[i] = Integer.parseInt(st.nextToken());
			}

			result = Year; // 1년 이용권으로 초기화

			dfs(0);

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth) {
		if (depth == 12) {
			simulation(); // 가격 계산
			return;
		}

		// 일일 이용권 사용
		ticket[depth] = 1;
		dfs(depth + 1);

		// 한달 이용권 사용
		ticket[depth] = 2;
		dfs(depth + 1);

		// 3달 이용권 사용
		int max = Math.min(depth + 3, 12);
		for (int i = depth; i < max; i++) {
			ticket[i] = 3;
		}
		dfs(max);
	}

	static void simulation() {
		int sum = 0;
		for (int i = 0; i < 12; i++) {
			if (ticket[i] == 1) { // 일일 이용권
				sum += use[i] * Day;
			} else if (ticket[i] == 2) { // 한달 이용권
				sum += Month;

			} else { // 3달 이용권
				sum += Month_3;
				i += 2;
			}
		}

		if (result > sum)
			result = sum; // 최소값 갱신
	}
}