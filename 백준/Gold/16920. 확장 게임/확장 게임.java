import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, P;
	static int map[][];
	static int direct[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int S[];
	static int count[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		S = new int[P+1];
		count = new int[P+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<= P; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		
		map = new int[N][M];
		
		List<Queue<Node>> list = new ArrayList<>();
		for(int i=0; i<=P; i++) {
			list.add(new ArrayDeque<>());
		}
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				if(str.charAt(j) == '#') {
					map[i][j] = -1;
				} else if(str.charAt(j) == '.') {
					map[i][j] = 0;
				} else {
					int num = str.charAt(j) -'0';
					list.get(num).add(new Node(i,j));
					map[i][j] = num;
					count[num]++;
				}
			}
		}

		while (true) {
			for(int i=1; i<=P; i++) {
				list.set(i, bfs(list.get(i), i));
			}
			
			boolean flag = false;
			for(int i=1; i<=P; i++) {
				if(!list.get(i).isEmpty()) {
					flag = true;
					break;
				}
			}
			if(!flag) break;
		}
		for(int i=1; i<=P; i++) {
			sb.append(count[i]).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static Queue<Node> bfs(Queue<Node> q, int num) {
		Queue<Node> nextQ = new ArrayDeque<>();
		while(!q.isEmpty()) {
			Node node = q.poll();

			if (node.count == S[num]) {
				nextQ.add(new Node(node.x, node.y));
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int row = node.x + direct[i][0];
				int col = node.y + direct[i][1];

				if (row < 0 || row >= N || col < 0 || col >= M)
					continue;

				if (map[row][col] != 0)
					continue;
				
				map[row][col] = num;
				count[num]++;
				q.add(new Node(row,col, node.count + 1));
			}
		}
		return nextQ;
	}

	static class Node {
		int x, y, count;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", count=" + count + "]";
		}
	}
}