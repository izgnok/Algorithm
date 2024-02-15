import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] in_degree;
	static List<List<Integer>> graph = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		in_degree = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			in_degree[b]++;
		}

		sort();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void sort() {
		Queue<Integer> q = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			if(in_degree[i] == 0) {
				q.add(i);
			}
		}
		
		for(int k=0; k<N; k++) {
			if(q.isEmpty()) {
				return;
			}
			int x = q.poll();
			sb.append(x + " ");
			
			for(int i=0; i< graph.get(x).size(); i++) {
				int next = graph.get(x).get(i);
				in_degree[next]--;
				if(in_degree[next] == 0) q.add(next);
			}
		}
	}

}