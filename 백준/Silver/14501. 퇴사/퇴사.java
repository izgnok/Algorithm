import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
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

		Node[] input = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int work = Integer.parseInt(st.nextToken());
			int money = Integer.parseInt(st.nextToken());
			input[i] = new Node(work, money);
		}

		int[] DP = new int[N + 1];
		for (int i =N; i >=1 ; i--) {
			int k = i + input[i].work - 1; // 일이 끝나는 시점
			if( k > N ) {
				if(i==N) DP[i] = 0;
				else DP[i] = DP[i+1];
			}
			else {
				if (i==N) DP[i] = input[i].money;
				else if(k+1 <= N )DP[i] = Math.max(DP[i+1], DP[k+1] + input[i].money);
				else DP[i] = Math.max(DP[i+1], input[i].money);
			}

		}
		sb.append(DP[1]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		int work, money;

		public Node(int work, int money) {
			this.work = work;
			this.money = money;
		}
	}
}