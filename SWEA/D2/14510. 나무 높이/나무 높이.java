import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int result;

	static int[] tree;
	static int max_tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());

			tree = new int[N];
			max_tree = 0;
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < N; i++) { // 입력받으면서 최고 높이의 나무 길이저장
				tree[i] = Integer.parseInt(st.nextToken());
				if (max_tree < tree[i]) {
					max_tree = tree[i];
				}
			}

			int a = 0; // 필요한 짝수일
			int b = 0; // 필요한 홀수일
			for (int i = 0; i < N; i++) {
				int k = max_tree - tree[i];
				if (k == 0)
					continue; // 이미 최대 높이인 경우
				a += k / 2;
				b += k % 2;
			}

			// 필요한 짝수일이 더 많은 경우 짝수일을 홀수일 * 2로 만들어준다. ( 짝수일과 홀수일의 차가 1보다 작아질때까지 )
			if (a > b) {
				while (Math.abs(a - b) > 1) {
					a--;
					b += 2;
				}
			}

			if (a > b || a == b) { // 짝수일이 하루 더 필요하거나, 필요한 짝수일과 홀수일이 같은 경우 
				result = 2 * a;
			} else { // 홀수일이 더많이 필요한 경우 
				result = 2 * b - 1;
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}