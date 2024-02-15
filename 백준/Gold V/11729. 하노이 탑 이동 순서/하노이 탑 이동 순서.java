import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		BigInteger count = new BigInteger("2");
		count = count.pow(N).subtract(new BigInteger("1"));
		sb.append(count).append("\n");
		if(N<=20) {
			hanoi(N,1,2,3);
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void hanoi(int n, int start, int mid, int end) {
		if(n==0) {
			return;
		}
		hanoi(n-1, start, end, mid);
		sb.append(start + " " + end + "\n");
		//sb.append(start + " " + end + "\n");
		hanoi(n-1 ,mid, start, end);
		
	}
}