import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N + 1][20];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i][0] = Integer.parseInt(st.nextToken());
		}

		for (int k = 1; k < 20; k++) {
			for (int i = 1; i <= N; i++) {
				arr[i][k] = arr[arr[i][k - 1]][k - 1];
			}
		}

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			while (n >= 1) {
				for (int k = 19; k >= 0; k--) {
					if (Math.pow(2, k) <= n) {
						x = arr[x][k];
						n -= Math.pow(2, k);
						break;
					}
				}
			}

			sb.append(x).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}