import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int result;
	static HashMap<Node, Integer> map;
	static Queue<Node> q;
	static int[][] direct = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static boolean death[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			q = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) * 2;
				int y = Integer.parseInt(st.nextToken()) * 2; // 1번에 0.5초씩 이동하기 위해서, 2배씩 늘려줌
				int dir = Integer.parseInt(st.nextToken());
				int energy = Integer.parseInt(st.nextToken());
				Node node = new Node(x, y, dir, energy, i);
				q.add(node);
			}

			result = 0;
			death = new boolean[N];
			while (!q.isEmpty()) { // 충돌가능성이 있는 원소가 남아있을때까지
				Queue<Node> next = new ArrayDeque<>(); // 다음번 움직임에 충돌 가능성이 있는 원소들
				map = new HashMap<>(); // 충돌감지 변수
				int size = q.size(); // 큐 사이즈
				for (int i = 0; i < size; i++) {
					Node node = q.poll();
					if (death[node.num]) {
						result += node.energy;
						continue; // 후충돌로 사망한 경우
					}
					
					int row = node.x + direct[node.dir][0];
					int col = node.y + direct[node.dir][1]; // 이동
					
					if (row < -2000 || row > 2000 || col < -2000 || col > 2000) // 충돌 가능성이 사라지는 경우
						continue;

					if (map.containsKey(new Node(row, col))) { // 충돌하는 경우
						result += node.energy;
						death[map.get(new Node(row, col))] = true; // 사망체크
						death[node.num] = true; // 사망체크
						continue;
					}
					map.put(new Node(row, col), node.num); // 이동체크
					next.add(new Node(row, col, node.dir, node.energy, node.num)); // 다음에 충돌가능성있음
				}
				q = next; // 복사
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		int x, y, dir, energy, num;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y,  int dir, int energy, int num) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", energy=" + energy + ", dir=" + dir + "]";
		}

		@Override
		public int hashCode() {
			int result = 17;
			result = 31 * result * x;
			result = 31 * result * y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			return x == other.x && y == other.y;
		}
	}
}