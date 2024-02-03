import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, n;
	static int result[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		result = new int[N];

		for (int i = 2; i <= 9; i++) {
			result[0] = i;
			dfs(0);
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth) {
		int su = 0;
		for (int i = 0; i <= depth; i++) {
			su += result[i] * (int) Math.pow(10, depth - i);
		}
		if (!sosu(su))
			return; // 소수가 아닌 경우
		if (depth == N-1) {
			for (int r : result) {
				sb.append(r);
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= 9; i++) {
			result[depth+1] = i;
			dfs(depth + 1);
		}
	}
	
	static boolean sosu(int num) {
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
}