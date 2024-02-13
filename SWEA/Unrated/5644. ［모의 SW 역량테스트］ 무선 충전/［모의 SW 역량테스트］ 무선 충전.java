import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int[] moveA;
	static int[] moveB;
	static List[][] map;
	static int idx, A_x, A_y, B_x, B_y, result;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int test_case = 0;
		while (test_case++ < T) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 움직이는 시간
			M = Integer.parseInt(st.nextToken()); // BC개수

			moveA = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			moveB = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}

			map = new List[11][11];
			for (int i = 1; i < 11; i++) {
				for (int j = 1; j < 11; j++) {
					map[i][j] = new ArrayList<BC>(); // 2차원 배열의 타입을 ArrayList로 초기화
				}
			}

			for (int k = 0; k < M; k++) { 
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()); // 열
				int y = Integer.parseInt(st.nextToken()); // 행
				int scope = Integer.parseInt(st.nextToken()); // 범위 
				int energy = Integer.parseInt(st.nextToken()); // 에너지

				for (int i = 1; i < 11; i++) {
					for (int j = 1; j < 11; j++) {
						if (Math.abs(y - i) + Math.abs(x - j) <= scope) { // 충전범위 ( (행 - 충전소 행) + (열 - 충전소 열) <= 범위 )
							map[i][j].add(new BC(energy, k));
						}
					}
				}
			}

			result = 0;
			idx = 0;
			A_x = 1;
			A_y = 1;
			B_x = 10;
			B_y = 10;
			while (idx <= N) {
				List<BC> listA = map[A_y][A_x]; // 해당 위치의 충전소 리스트
				List<BC> listB = map[B_y][B_x];

				Collections.sort(listA); // 에너지가 높은 순으로 정렬
				Collections.sort(listB);
				
				if (listA.size() != 0 && listB.size() != 0) { // 둘다 충전소가 있을때
					if (listA.get(0).num == listB.get(0).num) { // 제일 큰 충전소가 겹칠때
						if (listA.size() > 1 && listB.size() > 1) { // 둘 다 두개 이상의 충전소를 가질때

							if (listA.get(1).energy >= listB.get(1).energy) { // A의 두번째 충전소가 더 클때
								result += listA.get(1).energy;
								result += listB.get(0).energy;
							} else { // B의 두번째 충전소가 더 클 때
								result += listA.get(0).energy;
								result += listB.get(1).energy;
							}
						} else if (listA.size() > 1) { // 한명만 두개 이상의 충전소를 가질때
							result += listA.get(1).energy;
							result += listB.get(0).energy;
						} else if (listB.size() > 1) {
							result += listA.get(0).energy;
							result += listB.get(1).energy;
						} else { // 둘다 한개의 충전소만 가질때
							result += listA.get(0).energy;
						}
					} else { // 제일 큰 충전소가 안 겹칠때
						result += listA.get(0).energy;
						result += listB.get(0).energy;
					}
				} else if (listA.size() != 0) { // 한명만 충전소를 가질때
					result += listA.get(0).energy;
				} else if (listB.size() != 0) {
					result += listB.get(0).energy;
				} else {
				} // 아무도 충전소를 안가질때
				
				if(idx != N) move(moveA[idx], moveB[idx]); // 이동
				idx++;
			}
			sb.append("#" + test_case + " ");
			sb.append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void move(int directA, int directB) { // 이동

		if (directA == 1) {
			A_y--;
		} else if (directA == 2) {
			A_x++;
		} else if (directA == 3) {
			A_y++;
		} else if (directA == 4) {
			A_x--;
		} else {
		}

		if (directB == 1) {
			B_y--;
		} else if (directB == 2) {
			B_x++;
		} else if (directB == 3) {
			B_y++;
		} else if (directB == 4) {
			B_x--;
		} else {
		}
	}

	static class BC implements Comparable<BC> { // ArrayList에 담을 자료형
		int energy;
		int num;

		BC(int energy, int num) {
			this.energy = energy;
			this.num = num;
		}

		@Override
		public int compareTo(BC o) {
			return o.energy - this.energy;
		}

	}
}