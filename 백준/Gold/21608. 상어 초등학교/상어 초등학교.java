import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] input = new int[N * N + 1];
		int[][] student = new int[N * N + 1][4];
		int[][] result = new int[N + 1][N + 1];
		for (int i = 1; i <= N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			input[i] = idx;
			for (int j = 0; j < 4; j++) {
				student[idx][j] = Integer.parseInt(st.nextToken());
			}
		}

		result[2][2] = input[1];
		int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		for (int k = 2; k <= N * N; k++) {
			int max = 0;
			int max_bin = 0;
			int idx = 0, jdx = 0;
			for (int i = N; i > 0; i--) {
				for (int j = N; j > 0; j--) {
					if (result[i][j] != 0)
						continue;
					int count = 0;
					int bin = 0;
					for (int t = 0; t < 4; t++) {
						int row = i + direct[t][0];
						int col = j + direct[t][1];
						if (row >= 1 && row <= N && col >= 1 && col <= N) {
							for (int p = 0; p < 4; p++) {
								if (result[row][col] == student[input[k]][p])
									count++;
							}
							if (result[row][col] == 0)
								bin++;
						}
					}
					if (max < count) {
						max = count;
						max_bin = bin;
						idx = i;
						jdx = j;
					}
					else if(max==count) {
						if(max_bin <= bin) {
							max_bin = bin;
							idx = i;
							jdx = j;
						}
					}
				}
			}
			result[idx][jdx] = input[k];
		}

		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int count = 0;
				for (int t = 0; t < 4; t++) {
					int row = i + direct[t][0];
					int col = j + direct[t][1];

					if (row >= 1 && row <= N && col >= 1 && col <= N) {
						for (int p = 0; p < 4; p++) {
							if (result[row][col] == student[result[i][j]][p])
								count++;
						}
					}
				}
				if (count == 0) {
				} else if (count == 1) {
					sum += 1;
				} else if (count == 2) {
					sum += 10;
				} else if (count == 3) {
					sum += 100;
				} else {
					sum += 1000;
				}
			}
		}
		sb.append(sum);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}