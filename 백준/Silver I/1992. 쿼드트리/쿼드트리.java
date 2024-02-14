import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str= br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		check(map, N);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void check(int[][] cur, int size) {

		int zero = 0;
		int one = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (cur[i][j] == 0)
					zero++;
				else
					one++;
			}
		}
		if (zero != 0 && one != 0) { // 같지 않을때
			int tmp_size = size / 2;
			int arr1[][] = divide(cur, size, tmp_size, 1);
			int arr2[][] = divide(cur, size, tmp_size, 2);
			int arr3[][] = divide(cur, size, tmp_size, 3);
			int arr4[][] = divide(cur, size, tmp_size, 4);

			sb.append("(");
			check(arr1, tmp_size);
			check(arr2, tmp_size);
			check(arr3, tmp_size);
			check(arr4, tmp_size);
			sb.append(")");

		} else { // 모두 같은 경우
			if (zero != 0) {
				sb.append("0");
			} else {
				sb.append("1");
			}
		}
	}

	static int[][] divide(int[][] cur, int pre_size, int size, int direct) {

		int[][] tmp = new int[size][size];
		int idx = 0;
		int jdx = 0;
		switch (direct) {
		case 1:
			for (int i = 0; i < size; i++) {
				jdx = 0;
				for (int j = 0; j < size; j++) {
					tmp[idx][jdx++] = cur[i][j];
				}
				idx++;
			}
			return tmp;

		case 2:
			for (int i = 0; i < size; i++) {
				jdx = 0;
				for (int j = size; j < pre_size; j++) {
					tmp[idx][jdx++] = cur[i][j];
				}
				idx++;
			}
			return tmp;
		case 3:
			for (int i = size; i < pre_size; i++) {
				jdx = 0;
				for (int j = 0; j < size; j++) {
					tmp[idx][jdx++] = cur[i][j];
				}
				idx++;
			}
			return tmp;

		case 4:
			for (int i = size; i < pre_size; i++) {
				jdx = 0;
				for (int j = size; j < pre_size; j++) {
					tmp[idx][jdx++] = cur[i][j];
				}
				idx++;
			}
			return tmp;
		default:
			return null;
		}
	}
}