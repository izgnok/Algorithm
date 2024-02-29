import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map; // 지도
	static int group_count; // 섬 개수
	static int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상 , 하 , 좌, 우
	static int result;

	static List<List<Edge>> graph;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 입력받기
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 1) {
					map[i][j] = -1;
				} else
					map[i][j] = 0;
			}
		}

		// 그룹으로 묶기
		group_count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == -1) {
					group_count++;
					group(new Node(i, j));
				}
			}
		}

		// 섬개수만큼 리스트 생성
		graph = new ArrayList<>();
		for (int i = 0; i <= group_count; i++) {
			graph.add(new ArrayList<>());
		}

		// 가능한 모든 간선 추가하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) { // 섬이있을때
					for (int k = 0; k < 4; k++) { // 상하좌우로 간선추가
						addEdge(new Node(i, j), k);
					}
				}
			}
		}

		// MST알고리즘
		result = 0;
		visit = new boolean[group_count + 1];
		prim();

		// 모든 섬이 다 연결되었는지 확인
		for (int i = 1; i <= group_count; i++) {
			if (!visit[i]) {
				result = -1;
				break;
			}
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void addEdge(Node std, int dir) {
		int row = std.x;
		int col = std.y;
		int num = map[row][col]; // 그룹번호
		int next = 0; // 연결된 섬의 그룹번호
		int cost = 0; // 다리길이
		boolean possible = true; // 다리건설 가능 여부

		while (true) {
			row = row + direct[dir][0];
			col = col + direct[dir][1];
			if (row < 0 || row >= N || col < 0 || col >= M) { // 섬이나타나지않음
				possible = false; // 다리건설 불가
				break;
			}
			if (map[row][col] == num) { // 같은섬인경우
				possible = false; // 다리건설 불가
				break;
			}

			if (map[row][col] == 0) { // 빈칸
				cost++;
			} else { // 다른섬을 만난 경우
				next = map[row][col];
				break;
			}
		}

		if (possible && cost >= 2) {
			graph.get(num).add(new Edge(next, cost));
			graph.get(next).add(new Edge(num, cost));
		}
	}

	static void prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.add(new Edge(1, 0)); // 임의의 시작점과 비용 추가

		int count = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int x = edge.x;
			if (visit[x])
				continue;
			result += edge.cost;
			visit[x] = true;

			if (++count == group_count)
				break;

			for (int i = 0; i < graph.get(x).size(); i++) {
				Edge next = graph.get(x).get(i);
				if (visit[next.x])
					continue;
				pq.add(next);
			}
		}

	}

	static void group(Node start) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(start);
		map[start.x][start.y] = group_count;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int x = node.x;
			int y = node.y;

			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];

				if (row >= 0 && row < N && col >= 0 && col < M) {
					if (map[row][col] == -1) {
						map[row][col] = group_count;
						q.add(new Node(row, col));
					}
				}
			}
		}

	}

	static class Edge {
		int x, cost;

		Edge(int x, int cost) {
			this.x = x;
			this.cost = cost;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "정점: " + x + " cost: " + cost;
		}
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "X: " + x + " Y: " + y;
		}
	}
}