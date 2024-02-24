import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, K, A, B;
	static int result, time;
	static boolean possible;

	static int[] desk; // 접수하는데 걸리는 시간
	static int[] desk_check; // 접수창구가 비어있는지 여부 ( 손님이 도착한 시간이 >= 다른손님 접수가 끝난 시간이면 비어있음 )
	static int[] engine; // 정비하는데 걸리는 시간
	static int[] engine_check; // 정비창구가 비어있는지 여부 ( 손님이 도착한 시간이 >= 다른손님 정비가 끝난 시간이면 비어있음 )

	static int[] input; // 각 손님의 정비소의 도착한 시간
	static PriorityQueue<Node> people; // 손님의 번호와 정비소 도착 시간 ( 정비소 도착시간이 빠른순으로 정렬 )
	static PriorityQueue<Node2> desk_people; // 접수창구 번호와 접수가 끝나는 시간 ( 접구가 끝나는 시간이 빠른순으로 정렬, 시간이같으면 접수창구번호가 낮은순 )
	// 각 손님이 사용한 접수번호와 정비소 번호
	static int[] use_desk;
	static int[] use_engine;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			desk = new int[N + 1];
			desk_check = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				desk[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			engine = new int[M + 1];
			engine_check = new int[M + 1];
			for (int i = 1; i <= M; i++) {
				engine[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			people = new PriorityQueue<>();
			use_desk = new int[K + 1];
			use_engine = new int[K + 1];
			input = new int[K + 1];
			for (int i = 1; i <= K; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(input); // 빨리도착한 순으로 정렬
			for (int i = 1; i <= K; i++) {
				people.add(new Node(i, input[i]));
			}

			// 각 손님들의 접수번호 할당
			desk_people = new PriorityQueue<>();
			time = 0;
			while (!people.isEmpty()) {
				Node node = people.peek();
				if (node.time <= time) { // 정비소 도착
					possible = false;
					for (int i = 1; i <= N; i++) { // 비어있는 접수창구 찾기
						if (desk_check[i] > time)
							continue;
						people.poll();
						desk_check[i] = time + desk[i];
						desk_people.add(new Node2(node.num, i, time + desk[i]));
						use_desk[node.num] = i;
						possible = true;
						break;
					}
					// 비어있는 접수 창구가 없다면 시간 증가
					if (!possible)
						time++;
					continue;
				}
				time++; // 도착한 사람이 없다면 시간 증가
			}

			// 각 손님들의 정비번호 할당
			time = 0;
			while (!desk_people.isEmpty()) {
				Node2 node = desk_people.peek();
				if (node.time <= time) { // 접수 끝남
					possible = false;
					for (int i = 1; i <= M; i++) { // 비어있는 정비창구 찾기
						if (engine_check[i] > time)
							continue;
						desk_people.poll();
						engine_check[i] = time + engine[i];
						use_engine[node.num] = i;
						possible = true;
						break;
					}
					// 비어있는 정비창구가 없다면 시간 증가
					if (!possible)
						time++;
					continue;
				}
				time++; // 접수가 끝난 사람이 없다면 시간 증가
			}

			result = 0;
			for (int i = 1; i <= K; i++) { // 같은 접수창구와 정비창구를 사용한 손님번호의 합 구하기
				if (use_desk[i] == A && use_engine[i] == B) {
					result += i;
				}
			}
			if (result == 0) {
				result = -1;
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node implements Comparable<Node> {
		int num, time;

		Node(int num, int time) {
			this.num = num;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			if (this.time == o.time) {
				return this.num - o.num;
			}
			return this.time - o.time;
		}
	}

	static class Node2 implements Comparable<Node2> {
		int num, desk_num, time;

		Node2(int num, int desk_num, int time) {
			this.num = num;
			this.time = time;
			this.desk_num = desk_num;
		}

		@Override
		public int compareTo(Node2 o) {
			if (this.time == o.time) {
				return this.desk_num - o.desk_num;
			}
			return this.time - o.time;
		}
	}
}