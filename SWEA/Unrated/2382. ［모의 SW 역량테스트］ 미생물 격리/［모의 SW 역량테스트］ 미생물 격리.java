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
	static int N, M, K;
	static Node[][] map;

	static int[][] direct = { {}, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상 하 좌 우
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			map = new Node[N][N];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[row][col] = new Node(count, dir);
			}

			while (M-- > 0) {
				Node cur[][] = new Node[N][N]; // 임시 배열
				int max[][] = new int[N][N]; // 현재 위치에서 가장 큰 미생물 수

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] == null)
							continue; // 비어있는 경우 패스

						Node node = map[i][j];
						int row = i + direct[node.dir][0];
						int col = j + direct[node.dir][1];

						if (row >= 0 && row < N && col >= 0 && col < N) {
							if (row == 0 || row == N - 1 || col == 0 || col == N - 1) { // 테두리에 들어간 경우 ( 합쳐지는 경우 없음 )
								if (node.count / 2 != 0) { // 미생물이 남아있는 경우에만 저장
									int dir = node.dir;
									if (dir == 1)
										dir = 2;
									else if (dir == 2)
										dir = 1;
									else if (dir == 3)
										dir = 4;
									else
										dir = 3;
									cur[row][col] = new Node(node.count / 2, dir); // 미생물수 절반으로 감소, 방향 전환
								}
							} else {
								if (cur[row][col] == null) { // 비어있는 경우
									cur[row][col] = new Node(node.count, node.dir); // 그대로 들어감
									max[row][col] = node.count;
								} else {
									Node tmp = cur[row][col];
									if (node.count > max[row][col]) { // 뒤에 들어가는게 더 큰 경우
										cur[row][col] = new Node(node.count + tmp.count, node.dir);
										max[row][col] = node.count;
									} else if (node.count < max[row][col]) { // 뒤에 들어간게 더 작은 경우
										cur[row][col] = new Node(node.count + tmp.count, tmp.dir);
									} else { // 같은 경우는 없음
									}
								}
							}
						}
					}
				}
				map = cur; // 변화내용 저장
			}
			result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == null)
						continue;
					result += map[i][j].count;
				}
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node { // 군집안의 미생물 개수와 방향을 저장하기 위한 클래스
		int count, dir;

		Node(int count, int dir) {
			this.count = count;
			this.dir = dir;
		}
	}

}