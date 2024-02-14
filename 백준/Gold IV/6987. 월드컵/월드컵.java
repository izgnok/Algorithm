import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static Score[] score = new Score[6];
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());

			int total_win = 0, total_draw = 0, total_lose = 0;
			result = 1;
			for (int k = 0; k < 6; k++) {
				int win = Integer.parseInt(st.nextToken());
				int draw = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());
				score[k] = new Score(win, draw, lose);
				if (win + draw + lose != 5)
					result = 0;
				if (win > 5 || draw > 5 || lose > 5)
					result = 0;
				total_win += win;
				total_draw += draw;
				total_lose += lose;
			}
			if (total_win + total_draw + total_lose != 30)
				result = 0;
			if (total_win != total_lose)
				result = 0;
			if (total_draw % 2 != 0)
				result = 0;

			if (result != 0) {
				result = 0;
				dfs(0, 0, 1);
			}

			if (i != 3)
				sb.append(result + " ");
			else
				sb.append(result);
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth, int A, int B) {
		if (depth == 15) {
			result = 1;
			return;
		}

		// 이길때
		if(score[A].win > 0 && score[B].lose > 0) {
			score[A].win--;
			score[B].lose--;
			if (B + 1 == 6) {
				dfs(depth + 1, A + 1, A + 2);
			} else {
				dfs(depth + 1, A, B + 1);
			}
			if(result==1) return;
			score[A].win++;
			score[B].lose++;
		}

		// 비길때
		if(score[A].draw > 0 && score[B].draw > 0) {
			score[A].draw--;
			score[B].draw--;
			if (B + 1 == 6) {
				dfs(depth + 1, A + 1, A + 2);
			} else {
				dfs(depth + 1, A, B + 1);
			}
			if(result==1) return;
			score[A].draw++;
			score[B].draw++;
		}

		// 질때
		if(score[A].lose > 0 && score[B].win > 0) {
			score[A].lose--;
			score[B].win--;
			if (B + 1 == 6) {
				dfs(depth + 1, A + 1, A + 2);
			} else {
				dfs(depth + 1, A, B + 1);
			}
			if(result==1) return;
			score[A].lose++;
			score[B].win++;
		}

	}

	static class Score {
		int win, draw, lose;

		Score(int win, int draw, int lose) {
			this.win = win;
			this.draw = draw;
			this.lose = lose;
		}
	}
}