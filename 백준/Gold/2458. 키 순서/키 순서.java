import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	
	static int[] count;
	static List<ArrayList<Integer>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
		}
		
		
		count = new int[N+1];
		for(int i=1; i<=N; i++) {
			bfs(i);
		}
		
		int result = 0;
		for(int i=1; i<=N; i++) {
			if(count[i] == N-1) result++;
		}
		
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void bfs(int start) {
		boolean[] visit = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		visit[start] = true;
		
		while(!q.isEmpty()) {
			int idx = q.poll();
			for(int i=0; i < graph.get(idx).size(); i++) {
				int k = graph.get(idx).get(i);
				if(!visit[k]) {
					q.add(k);
					visit[k] = true;
					count[start]++;
					count[k]++;
				}
			}
			
		}
		
	}
}