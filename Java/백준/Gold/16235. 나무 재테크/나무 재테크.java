import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map; // 양분상태
	static int[][] add; // 겨울에 추가할 양분
	static HashMap<Node, PriorityQueue<Integer>> hash = new HashMap<>(); // 나무들
	static Queue<Node> death = new ArrayDeque<>(); // 죽은 나무들
	static Queue<Node> reproduce = new ArrayDeque<>(); // 가을에 번식할 나무들
	// 가을 번식 반향
	static int direct[][] = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) { // 초기에 모든 칸에는 양분이 5만큼 있음.
			Arrays.fill(map[i], 5);
		}
		add = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) { // 겨울마다 추가되는 양분 저장
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				add[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (M-- > 0) { // 기존 나무 정보
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			Node node = new Node(x, y);
			if (hash.containsKey(node)) {
				hash.get(node).add(age);
			} else {
				hash.put(node, new PriorityQueue<>());
				hash.get(node).add(age);
			}
		}

		while (K-- > 0) {
			spring();
			summer();
			fall();
			winter();
		}

		int result = 0; // 살아있는 나무 개수
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Node node = new Node(i, j);
				if (hash.containsKey(node)) {
					PriorityQueue<Integer> pq = hash.get(node);
					result += pq.size();
				}
			}
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void spring() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Node node = new Node(i, j);
				if (!hash.containsKey(node)) { // 해당칸에 나무가 없으면 패스
					continue;
				}

				PriorityQueue<Integer> pq = hash.get(node);
				PriorityQueue<Integer> next = new PriorityQueue<>();
				int size = pq.size();
				for (int k = 0; k < size; k++) {
					int age = pq.poll();
					if (map[i][j] >= age) {
						map[i][j] -= age;
						next.add(age + 1);
						if ((age + 1) % 5 == 0) {
							reproduce.add(new Node(i, j, age + 1));
						}
					} else {
						death.add(new Node(i, j, age));
					}
				}
				if (next.isEmpty()) { // 모든 나무가 다 죽은경우
					hash.remove(node);
				} else { // 하나 이상 살아 남은 경우
					hash.put(node, next);
				}
			}

		}
	}

	static void summer() {
		while (!death.isEmpty()) {
			Node node = death.poll();
			int x = node.x;
			int y = node.y;
			int age = node.age;
			map[x][y] += (age / 2);
		}
	}

	static void fall() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				Node node = new Node(i, j);
				if (!hash.containsKey(node)) { // 해당칸에 나무가 없는 경우
					continue;
				}

				while (!reproduce.isEmpty()) {
					Node tmp = reproduce.poll();
					int x = tmp.x;
					int y = tmp.y;
					for (int d = 0; d < 8; d++) {
						int row = x + direct[d][0];
						int col = y + direct[d][1];

						if (row < 1 || row > N || col < 1 || col > N)
							continue;

						Node next = new Node(row, col);
						if (hash.containsKey(next)) {
							hash.get(next).add(1);
						} else {
							hash.put(next, new PriorityQueue<>());
							hash.get(next).add(1);
						}
					}
				}
			}
		}
	}

	static void winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] += add[i][j];
			}
		}
	}

	static class Node {
		int x, y, age;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Node(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
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
			if (!(obj instanceof Node)) {
				return false;
			}

			Node other = (Node) obj;
			return this.x == other.x && this.y == other.y;
		}
	}
}