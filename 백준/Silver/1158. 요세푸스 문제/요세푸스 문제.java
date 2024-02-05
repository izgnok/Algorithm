import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}

		int idx = K-1;
		sb.append("<");
		while (!list.isEmpty()) {
			if(list.size() != 1) sb.append(list.get(idx) + ", ");
			if(list.size() == 1) sb.append(list.get(idx));
			list.remove(idx);

			int k = K-1;
			while(k>0) {
				if( idx > list.size()-1) idx = 0;
				idx++;
				if( idx > list.size()-1) idx = 0;
				k--;
			}
		}
		sb.append(">");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}