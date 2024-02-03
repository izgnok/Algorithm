import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K;
	static String[] Word;
	static int Result = 0;
	static int A_count, C_count, G_count, T_count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		Word = str.split("");
		
		st = new StringTokenizer(br.readLine());
		A_count = Integer.parseInt(st.nextToken());
		C_count = Integer.parseInt(st.nextToken());
		G_count = Integer.parseInt(st.nextToken());
		T_count = Integer.parseInt(st.nextToken());
		
		Deque<String> dq = new ArrayDeque<>();
		int a = 0, c = 0, g = 0, t = 0;
		for(int i=0; i<K; i++) {
			dq.addLast(Word[i]);
			if(Word[i].equals("A")) a++;
			if(Word[i].equals("C")) c++;
			if(Word[i].equals("G")) g++;
			if(Word[i].equals("T")) t++;
		}
		if( A_count <=a && C_count <=c && G_count <= g && T_count <=t) {
			Result++;
		}
		
		for(int i=K; i<N; i++) {
			String s = dq.pollFirst();
			if(s.equals("A")) a--;
			if(s.equals("C")) c--;
			if(s.equals("G")) g--;
			if(s.equals("T")) t--;
			
			dq.addLast(Word[i]);
			if(Word[i].equals("A")) a++;
			if(Word[i].equals("C")) c++;
			if(Word[i].equals("G")) g++;
			if(Word[i].equals("T")) t++;
					
			if( A_count <=a && C_count <=c && G_count <= g && T_count <=t) {
				Result++;
			}
		}
		
		
		
		sb.append(Result + "");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}