import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static char[] word;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			word = new char[str.length()];
			for (int k = 0; k < str.length(); k++) {
				word[k] = str.charAt(k);
			}

			int start = 0;
			int end = str.length() - 1;

			int result = 0;
			while (start <= end) {
				if (word[start] == word[end]) {
					start++;
					end--;
				} else {
					if (word[start + 1] == word[end] || word[start] == word[end - 1]) {
						result = 1;
						int tmp_start = start + 1;
						int tmp_end = end;

						while (tmp_start <= tmp_end) {
							if (word[tmp_start] == word[tmp_end]) {
								tmp_start++;
								tmp_end--;
							} else {
								result = 2;
								break;
							}
						}

						tmp_start = start;
						tmp_end = end - 1;
						if (result == 2) {
							result = 1;
							while (tmp_start <= tmp_end) {
								if (word[tmp_start] == word[tmp_end]) {
									tmp_start++;
									tmp_end--;
								} else {
									result = 2;
									break;
								}
							}
						}
					} else {
						result = 2;
					}
					break;
				}
			}
			sb.append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}