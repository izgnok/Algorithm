import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[][] input = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ArrayList<Queue<Integer>> list = new ArrayList<>();

		int start = 0;
		int n = N;
		int m = M;
		while (true) {
			int x = start;
			int y = start;
			Queue<Integer> q = new ArrayDeque<>();
			int count = 0;
			int direct = 1; // 우, 하, 좌, 상
			
			int T = 0;
			if(n-2!=0 && m-2 !=0) T = n * m - ( (n-2) * (m-2));
			else T = n*m;
			
			for (int i = 0; i < T; i++) {
				q.add(input[x][y]);

				if (direct == 1) {
					y++;
				} else if (direct == 2) {
					x++;
				} else if (direct == 3) {
					y--;
				} else {
					x--;
				}
				count++;
				if (direct == 1 || direct == 3) {
					if (count == m - 1) {
						direct++;
						count = 0;
					}
				} else if (direct == 2 || direct == 4) {
					if (count == n - 1) {
						direct++;
						count = 0;
					}
				}
				if (direct == 5)
					direct = 1;
			}
			list.add(q);
			start++;
			n = n - 2;
			m = m - 2;
			if (n == 0 || m == 0)
				break;
		}
		
		for(int i=0; i< list.size(); i++) {
			for(int j=0; j<R; j++) {
				list.get(i).add(list.get(i).poll());
			}
		}
		
		int[][] result = new int[N][M];
		start = 0;
		n = N;
		m = M;
		while (true) {
			int x = start;
			int y = start;
			int count = 0;
			int direct = 1; // 우, 하, 좌, 상
			
			int T = 0;
			if(n-2!=0 && m-2 !=0) T = n * m - ( (n-2) * (m-2));
			else T = n*m;
			
			for (int i = 0; i < T; i++) {
				result[x][y] = list.get(start).poll();

				if (direct == 1) {
					y++;
				} else if (direct == 2) {
					x++;
				} else if (direct == 3) {
					y--;
				} else {
					x--;
				}
				count++;
				if (direct == 1 || direct == 3) {
					if (count == m - 1) {
						direct++;
						count = 0;
					}
				} else if (direct == 2 || direct == 4) {
					if (count == n - 1) {
						direct++;
						count = 0;
					}
				}
				if (direct == 5)
					direct = 1;
			}
			start++;
			n = n - 2;
			m = m - 2;
			if (n == 0 || m == 0)
				break;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(result[i][j] + " ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}