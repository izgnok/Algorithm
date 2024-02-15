import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
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
	static int N, M, Start;
	static List<List<Integer>> graph = new ArrayList<>();;
	static boolean[] dfs_visit;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Start = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		for(int i=1; i<=N; i++) {
			Collections.sort(graph.get(i));
		}
		
		dfs_visit = new boolean[N+1];
		dfs(Start);
		sb.append("\n");
		bfs();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int x) {
		
		dfs_visit[x] = true;
		sb.append(x + " ");
		
		for(int i=0; i< graph.get(x).size(); i++) {
			int next = graph.get(x).get(i);
			if(dfs_visit[next]) continue;
			dfs_visit[next] = true;
			dfs(next);
		}
	}

	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[N+1];
		q.add(Start);
		visit[Start] = true;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			sb.append(x + " ");
			
			for(int i=0; i< graph.get(x).size(); i++) {
				int next = graph.get(x).get(i);
				if(visit[next]) continue;
				q.add(next);
				visit[next] = true;
			}
		}
		
	}
}