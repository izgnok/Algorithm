import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		Node[] input = new Node[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			input[i] = new Node(Integer.parseInt(st.nextToken()), i);
		}

		Deque<Node> dq = new ArrayDeque<>();
		int[] result = new int[N+1];
		for(int i=N; i>0; i--) {
			if(dq.isEmpty()) {
				dq.addLast(input[i]);
				continue;
			}
			
			int t = 1;
			while(true) {
				if(dq.isEmpty()) {
					dq.addLast(input[i]);
					break;
				}
				if(input[i].num >= dq.peekLast().num) {
					result[dq.peekLast().x] = i;
					dq.pollLast();
				}
				else {
					dq.addLast(input[i]);
					break;
				}
				t++;
			}
		}
		for(int i=1; i<=N; i++) {
			sb.append(result[i] + " ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static class Node {
		int num;
		int x;
		
		Node (int num, int x) {
			this.num = num;
			this.x = x;
		}
	}
}