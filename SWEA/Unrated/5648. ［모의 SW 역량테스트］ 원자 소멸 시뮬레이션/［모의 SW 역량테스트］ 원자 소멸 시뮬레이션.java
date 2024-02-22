import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, result;
	static int[][] direct = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

	static HashMap<Node, Integer> visit;
	static HashSet<Integer> death;
	static int[] energy_list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			Queue<Node> q = new ArrayDeque<>();
			energy_list = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = (1000 + Integer.parseInt(st.nextToken())) * 2;
				int y = (1000 + Integer.parseInt(st.nextToken())) * 2;
				int direct = Integer.parseInt(st.nextToken());
				int energy = Integer.parseInt(st.nextToken());
				q.add(new Node(i, x, y, direct, energy));
				energy_list[i] = energy;
			}

			// 시뮬레이션
			death = new HashSet<>();
			while (true) {
				Queue<Node> tmp = new ArrayDeque<>();
				visit = new HashMap<>();

				while (!q.isEmpty()) {
					Node node = q.poll(); // 이동하려는 원소 추출

					int x = node.x + direct[node.direct][1];
					int y = node.y + direct[node.direct][0];
					if (death.contains(node.num)) { // 이미 죽은경우
						continue;
					}

					if (x < 0 || x >= 4001 || y < 0 || y >= 4001) { // 범위밖으로 벗어난 경우 더 이상 충돌 가능성 없음
						continue;
					}

					Node next = new Node(node.num, x, y, node.direct, node.energy);

					if (!visit.containsKey(next)) { // 이동 가능한 경우
						visit.put(next, node.num);
						tmp.add(next);
					} else { // 이동 불가능한 경우 ( 충돌 )
						int pre = visit.get(next);
						death.add(pre);
						death.add(node.num);
					}
				}
				if (tmp.isEmpty()) { // 더이상 충돌가능성이있는 원소가 없는 경우
					break;
				}
				q = tmp;
			}

			result = 0;
			Iterator<Integer> iter = death.iterator(); // 죽은 세포들의 에너지 합 구하기
			while (iter.hasNext()) {
				result += energy_list[iter.next()];
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		int num, x, y, energy, direct;

		Node(int num, int x, int y, int direct, int energy) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.direct = direct;
			this.energy = energy;
		}

		@Override
		public int hashCode() {
			int result = 17;
			result = 31 * result + x;
			result = 31 * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Node)) {
				return false;
			}
			Node node = (Node) obj;
			return this.x == node.x && this.y == node.y;
		}
	}
}