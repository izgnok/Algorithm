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
import java.util.TreeSet;

public class Solution {
	static int N;

	static int result; // 결과값
	static boolean visit[]; // 1번계단으로 갈 사람 체크하기 위한 배열

	static List<Loc> floor; // 사람의 좌표를 저장한 리스트
	static List<Loc> human; // 계단의 좌표를 저장한 리스트
	static List<List<Integer>> map; // 사람과 계단 사이의 정보를 저장한 리스트
	static int[] time; // 계단을 내려가는 시간

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			floor = new ArrayList<>();
			human = new ArrayList<>();
			time = new int[2];
			int idx = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 1) {
						human.add(new Loc(i, j));
					} else if (num > 1) {
						floor.add(new Loc(i, j));
						time[idx++] = num;
					}
				}
			}

			map = new ArrayList<>(); // 사람 수 만큼 2차원 배열 초기화
			for (int i = 0; i < human.size(); i++) {
				map.add(new ArrayList<>());
			}

			for (int i = 0; i < human.size(); i++) { // 사람과 계단 사이의 거리 정보 저장
				for (int j = 0; j < floor.size(); j++) {
					int dist = Math.abs(human.get(i).x - floor.get(j).x) + Math.abs(human.get(i).y - floor.get(j).y);
					map.get(i).add(dist);
				}
			}

			visit = new boolean[human.size()]; // 사람수 만큼 배열 사이즈 초기화
			result = Integer.MAX_VALUE; // 결과값 초기화

			dfs(0);

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth) { // 1번계단을 이용할 사람 뽑기 ( 부분집합 )
		if (depth == human.size()) {
			// 해당 조합에서의 시간 계산
			simulation();
			return;
		}

		visit[depth] = true;
		dfs(depth + 1);
		visit[depth] = false;
		dfs(depth + 1);
	}

	static void simulation() {
		PriorityQueue<Integer> pq1 = new PriorityQueue<>((o1, o2) -> o1 - o2); // 거리가 가까운 순부터 계단의 진입
		PriorityQueue<Integer> pq2 = new PriorityQueue<>((o1, o2) -> o1 - o2);
		for (int i = 0; i < human.size(); i++) {
			if (visit[i]) {
				pq1.add(map.get(i).get(0)); // 해당 사람의 1번계단과의 거리를 저장
			} else {
				pq2.add(map.get(i).get(1)); // 해당 사람의 2번계단과의 거리를 저장
			}
		}

		// 1번 계단
		int sum1 = 0; // 총 시간
		Queue<Integer> cur = new ArrayDeque<>();
		while (!pq1.isEmpty()) {
			while (!cur.isEmpty()) {
				if (sum1 >= cur.peek() + time[0]) { // 계단을 다 내려간 경우
					cur.poll();
				} else {
					break;
				}
			}
			if (sum1 > pq1.peek() && cur.size() != 3) { // 도착시간보다 현재 시간이 더 많이 흐른 경우 , 정원이 남아있는 경우
				pq1.poll();
				cur.add(sum1);
				continue;
			}
			sum1++;
		}
		while (!cur.isEmpty()) { // 계단에 남아있는 경우
			if (sum1 >= cur.peek() + time[0]) {
				cur.poll();
			}
			else sum1++;
		}

		// 2번 계단
		int sum2 = 0; // 총 시간
		cur = new ArrayDeque<>();
		while (!pq2.isEmpty()) {
			while (!cur.isEmpty()) {
				if (sum2 >= cur.peek() + time[1]) { // 계단을 다 내려간 경우
					cur.poll();
				} else {
					break;
				}
			}
			if (sum2 > pq2.peek() && cur.size() != 3) { // 도착시간보다 현재 시간이 더 많이 흐른 경우 , 정원이 남아있는 경우
				pq2.poll();
				cur.add(sum2);
				continue;
			}
			sum2++;
		}
		while (!cur.isEmpty()) { // 계단에 남아있는 경우
			if (sum2 >= cur.peek() + time[1]) {
				cur.poll();
			}
			else sum2++;
		}

		int sum = Math.max(sum1, sum2); // 1번계단과 2번계단 중 더 오래 걸린걸 저장
		if (result > sum)
			result = sum; // 최솟값 갱신
	}

	static class Loc { // 좌표를 저장하기 위한 객체
		int x, y;

		Loc(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}