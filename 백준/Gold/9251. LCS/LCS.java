import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int result;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		// StringTokenizer st = new StringTokenizer(br.readLine());
		String str1 = br.readLine();
		String str2 = br.readLine();

		char[] word1 = new char[str1.length() + 1];
		char[] word2 = new char[str2.length() + 1];

		for (int i = 1; i <= str1.length(); i++) {
			word1[i] = str1.charAt(i-1);
		}
		for (int i = 1; i <= str2.length(); i++) {
			word2[i] = str2.charAt(i-1);
		}

		int[][] DP = new int[str1.length() + 1][str2.length() + 1];

		for (int i = 0; i <= str1.length(); i++) {
			for (int j = 0; j <= str2.length(); j++) {
				if (i == 0 || j == 0) {
					DP[i][j] = 0;
				} else if (word1[i] == word2[j]) {
					DP[i][j] = DP[i - 1][j - 1] + 1;
				} else {
					DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
				}
			}
		}

		sb.append(DP[str1.length()][str2.length()]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}