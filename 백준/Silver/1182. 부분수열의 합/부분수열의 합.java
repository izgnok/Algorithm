import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static int result = 0;
	static int max_plus = 0;
	static int max_minus = 0;
	static int N, S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] > 0)
				max_plus += arr[i];
			if (arr[i] < 0)
				max_minus += arr[i];
		}

		for (int i = 0; i < N; i++) {
			back_track(arr, i, 0, max_plus, max_minus);
		}
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
	}

	public static void back_track(int[] arr, int idx, int score, int max_plus, int max_minus) {
		//Queue<Integer> q = new ArrayDeque<>();
		score += arr[idx];
		if(score == S) result++;
		if(arr[idx] > 0) {
			max_plus -= arr[idx];
		}
		if(arr[idx] < 0) {
			max_minus -= arr[idx];
		}
		for(int i=idx+1; i<N; i++) {
			if( S > score + max_plus ) return;
			if( S < score + max_minus ) return;
			back_track(arr, i, score, max_plus, max_minus);
		}
	}
}