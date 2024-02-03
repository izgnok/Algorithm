import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			dq.addLast(i);
		}
		int how = 1;
		while(dq.size() > 1) {
			if(how==1) {
				dq.pollFirst();
				how = 2;
			}
			else {
				int tmp = dq.pollFirst();
				dq.addLast(tmp);
				how = 1;
			}
		}
		sb.append(dq.pollFirst());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}