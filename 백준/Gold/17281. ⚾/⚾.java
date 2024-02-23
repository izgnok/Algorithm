import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, result = 0;
	static int[][] player;
	static boolean[] visit;
	static int[] order;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		player = new int[10][N];
		visit = new boolean[10];
		order = new int[10];
		for (int j = 0; j < N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 9; i++) {
				player[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visit[1] = true;
		order[4] = 1;
		dfs(1);

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth) {
		if (depth == 10) {
			int score = simulation();
			if (score > result) {
				result = score;
			}
			return;
		}

		if (depth == 4) { // 4번타자는 고정
			dfs(depth + 1);
		} else {
			for (int i = 2; i <= 9; i++) {
				if (visit[i])
					continue;
				visit[i] = true;
				order[depth] = i;
				dfs(depth + 1);
				order[depth] = 0;
				visit[i] = false;
			}
		}
	}

	static int simulation() {

		int sum = 0;
		int num = 1; // 1번타자부터 시작
		for (int j = 0; j < N; j++) {

			boolean runner[] = new boolean[3];
			int out = 0;
			while (out < 3) {
				if (num == 10) // 한바퀴돌면 초기화
					num = 1;
				int hit = player[order[num]][j];
				if (hit == 0) { // 아웃
					out++;
				} else if (hit == 1) { // 단타
					if (runner[2]) { // 3루주자가있는 경우 득점
						sum++;
						runner[2] = false;
					}
					for (int i = 1; i >= 0; i--) { // 1,2루 주자가있는 경우 한칸씩 앞으로
						if (runner[i]) {
							runner[i + 1] = true;
							runner[i] = false;
						}
					}
					runner[0] = true; // 타자는 1루주자로

				} else if (hit == 2) { // 2루타
					if (runner[1] && runner[2]) { // 2,3루주자가 있는 경우 2득점
						sum += 2;
						runner[1] = false;
						runner[2] = false;
					} else if (runner[1]) { // 2루주자만 있는 경우 1득점
						sum++;
						runner[1] = false;
					} else if (runner[2]) { // 3루주자만 있는 경우 1득점
						sum++;
						runner[2] = false;
					}
					if (runner[0]) { // 1루주자가있는 경우 3루로 이동
						runner[0] = false;
						runner[2] = true;
					}
					runner[1] = true; // 타자는 2루주자로

				} else if (hit == 3) { // 3루타

					for (int i = 0; i < 3; i++) { // 주자 수 만큼 득점
						if (runner[i]) {
							sum++;
							runner[i] = false;
						}
					}
					runner[2] = true; // 타자는 3루주자로

				} else { // 홈런
					for (int i = 0; i < 3; i++) { // 주자 수 만큼 득점
						if (runner[i]) {
							runner[i] = false;
							sum++;
						}
					}
					sum++; // 타자주자도 득점
				}
				num++;
			}
		}
		return sum;
	}
}