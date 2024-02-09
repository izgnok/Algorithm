import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean visit[][];
	static int[][] input;
	static int max, max_rainbow;
	static int result = 0;
	static int group_x, group_y;
	static boolean group[][];
	static Group[][] group_map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			max = 1; // 최고크기의 그룹블럭
			max_rainbow = 0;
			group_x = -1; // 기준블럭
			group_y = -1;
			group = new boolean[N][N]; // 이미 그룹에 포함되었는지 여부
			group_map = new Group[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (input[i][j] == -1 || input[i][j] == 0)
						continue; // 검은색블럭과 무지개블럭은 기준블럭이 될 수 없다
					if (group[i][j])
						continue; // 이미 그룹에 포함된 경우 패스
					if (input[i][j] == -2) // 빈칸인 경우 패
						continue;
					visit = new boolean[N][N];
					bfs(new Node(i, j));
				}
			}
			if (max == 1) // 그룹이 1개도 없는 경우
				break;
			score(max);
			delete(group_x, group_y);
			down();
			rotate();
			down();
		}
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void bfs(Node standard) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(standard);
		visit[standard.x][standard.y] = true;
		group_map[standard.x][standard.y] = new Group();

		int count = 1;
		int rainbow_count = 0;
		int[][] direct = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			int x = tmp.x;
			int y = tmp.y;
			group[x][y] = true;
			group_map[standard.x][standard.y].list.add(new Node(x, y)); // 기준 블럭 위치로 그룹화
			for (int i = 0; i < 4; i++) {
				int row = x + direct[i][0];
				int col = y + direct[i][1];
				if (row >= 0 && row < N && col >= 0 && col < N) { // 맵크기안일때
					if (!visit[row][col]) { // 아직 방문하지 않았을때
						if (input[row][col] == input[standard.x][standard.y] || input[row][col] == 0) { // 색상이 같거나,
																										// 무지개블럭인 경우
							visit[row][col] = true;
							q.add(new Node(row, col));
							count++;
							if (input[row][col] == 0)
								rainbow_count++;
						}
					}
				}
			}
		}
		if (count > max) { // 개수가 더 많으면 업데이트
			max = count;
			group_x = standard.x;
			group_y = standard.y;
			max_rainbow = rainbow_count;
		} else if (count == max) { // 무지개블럭이 더많으면 업데이트
			if (rainbow_count > max_rainbow) {
				max = count;
				group_x = standard.x;
				group_y = standard.y;
				max_rainbow = rainbow_count;
			} else if (rainbow_count == max_rainbow) { // 개수가 같으면 행과 열이 더 큰쪽으로 업데이트
				group_x = standard.x;
				group_y = standard.y;
			}
		}
	}

	static class Group {
		List<Node> list;

		Group() {
			list = new ArrayList<>();
		}
	}

	static class Node { // 좌표수를 저장하기 위한 클래스
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void down() { // 중력효과
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				if (input[i][j] == -1)
					continue; // 검은색블럭은 움직이지 않는다
				if (input[i][j] == -2)
					continue; // 빈칸인경우 패스
				int idx = i;
				while (input[idx + 1][j] == -2) { // 아래쪽이 빈칸이 아닐때까지 밀기
					input[idx + 1][j] = input[idx][j];
					input[idx][j] = -2;
					idx++;
					if (idx == N - 1)
						break;
				}
			}
		}
	}

	static void rotate() { // 90도 회전
		int[][] tmp = new int[N][N];
		int jdx = N - 1;
		for (int i = 0; i < N; i++) {
			int idx = 0;
			for (int j = 0; j < N; j++) {
				tmp[i][j] = input[idx][jdx];
				idx++;
			}
			jdx--;
		}
		input = tmp;
	}

	static void score(int max) { // 점수합산
		result += (int) Math.pow(max, 2);
	}

	static void delete(int x, int y) { // 사용블럭 제거
		for (int i = 0; i < group_map[x][y].list.size(); i++) {
			Node node = group_map[x][y].list.get(i);
			int row = node.x;
			int col = node.y;
			input[row][col] = -2;
		}
	}
}