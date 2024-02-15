import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long arr[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		arr = new long[N + 1][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());

			if (i == 0) {
				arr[N][0] = arr[i][0];
				arr[N][1] = arr[i][1];
			}
		}

		BigDecimal a = BigDecimal.valueOf(right());
		BigDecimal b = BigDecimal.valueOf(left());
		a = a.subtract(b);
		a = a.abs();
		a = a.divide(BigDecimal.valueOf(2.0));
		//무조건 소수점 첫번째 자리까지 표시
		a = a.setScale(1, RoundingMode.HALF_UP);
		

		sb.append(a);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static long right() {
		long sum = 0;
		for (int i = 0; i < N; i++) {
			sum += arr[i][0] * arr[i + 1][1];
		}
		return sum;
	}

	static long left() {
		long sum = 0;
		for (int i = 0; i < N; i++) {
			sum += arr[i][1] * arr[i + 1][0];
		}
		return sum;
	}
}