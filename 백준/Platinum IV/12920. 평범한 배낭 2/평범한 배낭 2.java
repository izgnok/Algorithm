import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		ArrayList<Node> input = new ArrayList<>();
		input.add(new Node(0, 0));
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int score = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			int tmp = 1;
			while (tmp <= k) {
				input.add(new Node(tmp * weight, tmp * score));
				k -= tmp;
				tmp *= 2;
			}
			if (k != 0) {
				input.add(new Node(k * weight, k * score));
			}
		}

		int[][] DP = new int[input.size()+ 1][W + 1];

		for (int i = 1; i < input.size(); i++) { // 현재 물건
			for (int j = 1; j <= W; j++) { // 현재 무게
				if (j >= input.get(i).weight) {
					DP[i][j] = Math.max(DP[i - 1][j], DP[i - 1][j - input.get(i).weight] + input.get(i).score);
				} else {
					DP[i][j] = DP[i - 1][j];
				}
			}
		}
		sb.append(DP[input.size()-1][W] + "\n");

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static class Node {
		int score, weight;

		Node(int weight, int score) {
			this.score = score;
			this.weight = weight;
		}
	}
}