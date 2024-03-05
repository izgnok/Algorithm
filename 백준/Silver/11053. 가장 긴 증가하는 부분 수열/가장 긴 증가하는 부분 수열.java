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

		int[] input = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		int[] DP = new int[N];
		for(int i=0; i<N; i++) {
			DP[i] = 1;
		}

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (input[i] > input[j]) {
					DP[i] = Math.max(DP[i], DP[j] + 1);
				}
			}
		}

		Arrays.sort(DP);
		sb.append(DP[N - 1]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}