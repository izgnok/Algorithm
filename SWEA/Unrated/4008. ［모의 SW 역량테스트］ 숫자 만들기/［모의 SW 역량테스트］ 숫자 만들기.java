import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, plus, minus, mul, div;
	static int max, min, result;
	static int[] oper;
	static int[] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			plus = Integer.parseInt(st.nextToken());
			minus = Integer.parseInt(st.nextToken());
			mul = Integer.parseInt(st.nextToken());
			div = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			num = new int[N];
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}

			oper = new int[N - 1];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			dfs(0);

			result = max - min;
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth) {
		if (depth == N - 1) {
			// 수식 계산
			sum(oper);
			return;
		}

		if (plus > 0) { // 더하기
			plus--;
			oper[depth] = 1;
			dfs(depth + 1);
			plus++;
		}

		if (minus > 0) {
			minus--;
			oper[depth] = 2;
			dfs(depth + 1);
			minus++;
		}

		if (mul > 0) {
			mul--;
			oper[depth] = 3;
			dfs(depth + 1);
			mul++;
		}

		if (div > 0) {
			div--;
			oper[depth] = 4;
			dfs(depth + 1);
			div++;
		}
	}

	static void sum(int[] arr) {
		int sum = num[0];
		boolean possible = true;

		for (int i = 1; i < N; i++) {
			if (arr[i - 1] == 1) {
				sum += num[i];
			} else if (arr[i - 1] == 2) {
				sum -= num[i];
			} else if (arr[i - 1] == 3) {
				sum *= num[i];
			} else if (arr[i - 1] == 4) {
				if (num[i] == 0) {
					possible = false;
					break;
				}
				sum /= num[i];
			}
		}

		if (possible) {
			if (max < sum)
				max = sum;
			if (min > sum)
				min = sum;
		}
	}
}