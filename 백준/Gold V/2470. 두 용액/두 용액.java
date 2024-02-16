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
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;

	static int[] list;
	static int x, y;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		list = new int[N];
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			list[i] = num;
		}
		Arrays.sort(list);
		int start = 0;
		int end = N - 1;
		while(end > start) {
			int sum = list[start] + list[end];
			if(result > Math.abs(sum)) {
				result = Math.abs(sum);
				x = list[start];
				y = list[end];
			}
			if(sum==0) {
				break;
			}
			else if(sum < 0) {
				start++;
			}
			else {
				end--;
			}
		}
		sb.append(x + " " + y);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}