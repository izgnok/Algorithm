import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

//(((1)))(2)
public class Main {
	static String[] result;
	static Pair[] pair = new Pair[10];
	static int idx = 0;
	static int str_idx = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		
		Deque<Integer> front = new ArrayDeque<>();
				
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == '(') front.addLast(i);
			if(str.charAt(i) == ')') {
				Pair tmp = new Pair(front.pollLast(), i);
				pair[idx++] = tmp;
			}
		}
		
		result = new String[(int) (Math.pow(2, idx)-1)];  // (2의 n제곱 -1)
		for(int i=0; i< idx; i++) {
			dfs(i, str);
		}
		
		Arrays.sort(result, Comparator.naturalOrder());
		Set<String> linkedHashSetStrList = new LinkedHashSet<>(Arrays.asList(result));
		
		Iterator<String> iter = linkedHashSetStrList.iterator();
		while(iter.hasNext()) {
			sb.append(iter.next() + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void dfs(int i, String cur) {
		if (i == idx)
			return;
		String str = "";
		for (int j = 0; j < cur.length(); j++) {
			if (pair[i].x == j || pair[i].y == j) {
				str += " ";
			} else
				str += cur.charAt(j);
		}
		result[str_idx++] = str.replaceAll(" ", "");

		for (int j = i + 1; j < idx; j++) {
			dfs(j, str);
		}
	}
}