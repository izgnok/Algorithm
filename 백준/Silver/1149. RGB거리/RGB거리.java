import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
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

		int[][] RGB = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			RGB[i][0] = Integer.parseInt(st.nextToken());
			RGB[i][1] = Integer.parseInt(st.nextToken());
			RGB[i][2] = Integer.parseInt(st.nextToken());
		}

		int[][] DP = new int[N][3];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 0) {
					DP[i][j] = RGB[i][j];
				} else {
					DP[i][j] = RGB[i][j] + Math.min(DP[i - 1][(j + 1) % 3], DP[i - 1][(j + 2) % 3]);
				}
			}
		}

		int result = Math.min(Math.min(DP[N - 1][0], DP[N - 1][1]), DP[N - 1][2]);
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}