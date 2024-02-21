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

public class Solution {
	static int N, M, K;

	static int[][] map;

	public static void main(String[] args) throws Exception {
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

			map = new int[501][501];
			int result = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.life - o1.life);
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int life = Integer.parseInt(st.nextToken());
					int row = 250 + i;
					int col = 250 + j;
					if (life != 0) {
						pq.add(new Node(row, col, life, life, 0));
						map[row][col] = 1;
						result++;
					}
				}
			}

			int time = 0;
			while (time <= K) {
				PriorityQueue<Node> pq2 = new PriorityQueue<>((o1, o2) -> o2.life - o1.life);
				while (!pq.isEmpty()) {
					Node node = pq.poll();
					if (node.birth > 0) { // 아직 대기중
						pq2.add(new Node(node.x, node.y, node.life, node.birth - 1, 0));

					} else if (node.birth == 0) { // 부활함
						pq2.add(new Node(node.x, node.y, node.life, -1, node.life));
					} else { // 번식하는중
						int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }; // 상, 하, 우, 좌
						for (int i = 0; i < 4; i++) {
							int row = node.x + direct[i][0];
							int col = node.y + direct[i][1];

							if (map[row][col] == 0) { // 칸이 비어있는 경우에만 증식 ( 시간이 더큰것부터 하기 때문에 )
								map[row][col] = 1;
								pq2.add(new Node(row, col, node.life, node.life - 1, 0));
								result++;
							}
						}
						if (node.death - 1 > 0) // 번식하고도 살아남은 경우
							pq2.add(new Node(node.x, node.y, node.life, -1, node.death - 1));
						else // 죽은 경우
							result--;
					}
				}
				pq = pq2; // 복사
				time++;
			}

			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		int x, y, life, birth, death;

		Node(int x, int y, int life, int birth, int death) {
			this.x = x;
			this.y = y;
			this.life = life;
			this.birth = birth;
			this.death = death;
		}
	}
}