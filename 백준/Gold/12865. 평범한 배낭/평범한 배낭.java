import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		Node[] input = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			input[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		int[][] DP = new int[N + 1][W + 1];

		for (int i = 1; i <= N; i++) { // 현재 물건
			for (int j = 1; j <= W; j++) { // 현재 무게
				if (j >= input[i].weight) {
					DP[i][j] = Math.max(DP[i - 1][j], DP[i - 1][j - input[i].weight] + input[i].score);
				} else {
					DP[i][j] = DP[i - 1][j];
				}
			}
		}
		sb.append(DP[N][W] + "\n");

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