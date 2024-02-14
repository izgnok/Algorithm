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
	static int N, M;
	static int[] input;
	static StringBuilder sb = new StringBuilder();
	static LinkedHashSet<String> hashSet = new LinkedHashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);

		dfs(0, new int[M]);

		Iterator iter = hashSet.iterator();
		while (iter.hasNext()) {
			sb.append(iter.next() + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int[] result) {
		if (depth == M) {
			String str = "";
			for (int r : result) {
				str += String.valueOf(r) + " ";
			}
			if (!hashSet.contains(str))
				hashSet.add(str);
			return;
		}

		int[] tmp = result.clone();

		for (int i = 0; i < N; i++) {
			if(depth == 0) {
				tmp[depth] = input[i];
				dfs(depth + 1, tmp);
			}
			else if (depth!=0 && tmp[depth - 1] <= input[i]) {
				tmp[depth] = input[i];
				dfs(depth + 1, tmp);
			}
		}
	}
}