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
	static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());

		if(M!=0) st = new StringTokenizer(br.readLine());
		boolean check[] = new boolean[10];
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			check[num] = true;
		}

		int result = Math.abs(N - 100);

		if (result != 0) {
			for (int i = 0; i <= 999999; i++) { // 0번부터 999999번까지 다이렉트로 누를 수 있는 경우의 수를 따져본다. ( 500000번 초과 채널도 갈수는있더라 )

				String str = String.valueOf(i);
				boolean possible = true;
				for (int j = 0; j < str.length(); j++) { // 해당 채널을 가기위한 모든 버튼 다 누를수 있는지 확인
					int k = str.charAt(j) - '0';
					if (check[k]) { // 해당 버튼이 고장난 경우
						possible = false;
						break;
					}
				}
				if (possible) { // 해당 채널을 다이렉트로 갈 수 있을 때
					int count = Math.abs(N - i) + str.length(); // 해당 채널에서 목표채널까지 +1,-1 횟수 + 해당 채널의 길이
					result = Math.min(result, count);
				}
			}
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}