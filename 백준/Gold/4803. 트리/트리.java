import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static List<List<Integer>> graph;
	static boolean cycle = false;
	static boolean[] visit;
	static int[] parent;
	static int result, count, depth;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int test_case = 1;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N==0 && M==0) break;
			graph = new ArrayList<>();
			for(int i=0; i<=N; i++) { // 그래프 정점개수만큼 초기화
				graph.add(new ArrayList<>());
			}
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());
				graph.get(n).add(m);
				graph.get(m).add(n);
			}
			visit = new boolean[N+1];
			parent = new int[N+1];
			result = 0;
			for(int i=1; i<=N; i++) {
				cycle = false;
				count = 1;
				depth = 0;
				bfs(i);
				if(!cycle && count-1 == depth) result++;
			}
			if(result == 0) {
				sb.append("Case " + test_case + ": No trees.\n");
			}
			else if(result == 1) {
				sb.append("Case " + test_case + ": There is one tree.\n");
			} 
			else {
				sb.append("Case " + test_case + ": A forest of " + result + " trees.\n");
			}
			test_case++;
		}	
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void bfs(int idx) {
		if(visit[idx]) {
			cycle = true;
			return;
		}
		visit[idx] = true;

		for(int i=0; i<graph.get(idx).size(); i++) {
			if(parent[idx] == graph.get(idx).get(i)) continue;
			parent[graph.get(idx).get(i)] = idx;
			count++;
			depth++;
			bfs(graph.get(idx).get(i));
		}
	}
}