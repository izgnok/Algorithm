import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	
	static List<String> input = new ArrayList<>(); 
	static List<String> result = new ArrayList<>();
	static int L, N;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			input.add(st.nextToken());
		}
		Collections.sort(input, (o1,o2) -> o1.charAt(0)- o2.charAt(0));
		
		for(int i=0; i<= input.size()-L; i++) {
			back_track(i,1, "", 0, 0);
		}
		
		for(int i=0; i<result.size(); i++) {
			sb.append(result.get(i)).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void back_track(int idx, int cur_len, String str, int mo_count, int ja_count) {
		String cur_str = str; // 깊은복사
		cur_str += input.get(idx);
		if(input.get(idx).equals("a") || input.get(idx).equals("e") || input.get(idx).equals("i") || input.get(idx).equals("o")  
				|| input.get(idx).equals("u")) {
			mo_count++;
		}
		else ja_count++;
		if(L==ja_count) return;
		if(L-mo_count < 2) return;
		if(cur_len == L) {
			result.add(cur_str);
			return;
		}
		for(int i=idx+1; i<input.size(); i++) {
			back_track(i, cur_len+1, cur_str, mo_count, ja_count);
		}
	}

}