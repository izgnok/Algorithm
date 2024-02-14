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
	static long N, M;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dfs(0, N);
		if(result == Integer.MAX_VALUE) result = -1;
		else result++;
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void dfs(int depth, long cur) {
		if(cur == M) {
			if(result > depth) result = depth;
		}
		if(cur > M) return;
		
		dfs(depth+1, cur*10 +1);
		dfs(depth+1, cur*2);
	}
}