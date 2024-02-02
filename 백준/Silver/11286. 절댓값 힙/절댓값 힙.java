import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer tk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tk.nextToken());
		
		PriorityQueue<Compare> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			int K = Integer.parseInt(br.readLine());
			if(K==0) {
				if(pq.isEmpty()) sb.append("0\n");
				else sb.append(pq.poll() + "\n");
			}
			else {
				pq.offer(new Compare(K));
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static class Compare implements Comparable<Compare>{
		int num;
		Compare(int num) {
			this.num = num;
		}
		@Override
		public int compareTo(Compare o) {
			if( Math.abs(this.num) != Math.abs(o.num)) {
				return Math.abs(this.num) - Math.abs(o.num);
			}
			return this.num - o.num;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return String.valueOf(this.num);
		}
	}
}