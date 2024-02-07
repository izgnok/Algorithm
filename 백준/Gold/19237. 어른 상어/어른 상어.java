import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R;
	static int result = 0;
	static ArrayList<int[][]> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		// 전체 상태 입력
		Node map[][] = new Node[N][N];
		Loc shark[] = new Loc[M + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num != 0) {
					shark[num] = new Loc(i, j);
					map[i][j] = new Node(num);
					map[i][j].ls.put(num, 0);
				} else {
					map[i][j] = new Node(-1); // 비어있는칸
				}
			}
		}
		// 초기 상어 방향 입력
		st = new StringTokenizer(br.readLine());
		int[] shark_direct = new int[M + 1];
		for (int i = 1; i <= M; i++) {
			shark_direct[i] = Integer.parseInt(st.nextToken());
		}
		// 상어의 우선순위 입력
		for (int i = 0; i <= M; i++) {
			list.add(new int[5][5]);
		}
		for (int k = 1; k <= M; k++) {
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= 4; j++) { // 우선순위
					list.get(k)[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

		int result = 1;
		int count = 0;
		while (result <= 1000) {
			// 상어 1번부터 한칸씩 이동
			boolean[][] now = new boolean[N][N];
			for (int i = 1; i <= M; i++) {
				int x = shark[i].x;
				int y = shark[i].y; // 현재 상어 위치
				if (x == -1 || y == -1)
					continue; // 제거된 상어는 패스
				boolean change = false;
				// 빈칸찾기
				for (int j = 1; j <= 4; j++) {
					int direct = list.get(i)[shark_direct[i]][j];
					if (direct == 1) {
						if (x - 1 >= 0) {
							if (map[x - 1][y].ls.size() == 0) { // 빈칸인 경우
								map[x][y].shark = -1;
								map[x - 1][y].shark = i;
								map[x - 1][y].ls.put(i, result);
								now[x - 1][y] = true; // 빈칸일때 들어감,원래 비어있던 공간
								shark[i].x = x - 1;
								shark[i].y = y;
								shark_direct[i] = 1;
								change = true;
								break;
							} else { // 같이 들어온 경우
								if (now[x - 1][y]) {
									map[x][y].shark = -1;
									shark[i].x = -1;
									shark[i].y = -1;
									shark_direct[i] = -1;
									count++; // 제거된 상어 카운트 올리기
									change = true;
									break;
								}
							}
						}
					} else if (direct == 2) {
						if (x + 1 < N) {
							if (map[x + 1][y].ls.size() == 0) {
								map[x][y].shark = -1;
								map[x + 1][y].shark = i;
								map[x + 1][y].ls.put(i, result);
								now[x + 1][y] = true; // 빈칸일때 들어감,원래 비어있던 공
								shark[i].x = x + 1;
								shark[i].y = y;
								shark_direct[i] = 2;
								change = true;
								break;
							} else { // 같이 들어온 경우
								if (now[x + 1][y]) {
									map[x][y].shark = -1;
									shark[i].x = -1;
									shark[i].y = -1;
									shark_direct[i] = -1;
									count++; // 제거된 상어 카운트 올리기
									change = true;
									break;
								}
							}
						}
					} else if (direct == 3) {
						if (y - 1 >= 0) {
							if (map[x][y - 1].ls.size() == 0) {
								map[x][y].shark = -1;
								map[x][y - 1].shark = i;
								map[x][y - 1].ls.put(i, result);
								now[x][y - 1] = true;
								shark[i].x = x;
								shark[i].y = y - 1;
								shark_direct[i] = 3;
								change = true;
								break;
							} else { // 같이 들어온 경우
								if (now[x][y - 1]) {
									map[x][y].shark = -1;
									shark[i].x = -1;
									shark[i].y = -1;
									shark_direct[i] = -1;
									count++; // 제거된 상어 카운트 올리기
									change = true;
									break;
								}
							}
						}
					} else {
						if (y + 1 < N) {
							if (map[x][y + 1].ls.size() == 0) {
								map[x][y].shark = -1;
								map[x][y + 1].shark = i;
								map[x][y + 1].ls.put(i, result);
								now[x][y + 1] = true;
								shark[i].x = x;
								shark[i].y = y + 1;
								shark_direct[i] = 4;
								change = true;
								break;
							} else { // 같이 들어온 경우
								if (now[x][y+1]) {
									map[x][y].shark = -1;
									shark[i].x = -1;
									shark[i].y = -1;
									shark_direct[i] = -1;
									count++; // 제거된 상어 카운트 올리기
									change = true;
									break;
								}
							}
						}
					}
				}
				// 자기 냄새찾기
				if (!change) {
					for (int j = 1; j <= 4; j++) {
						if (shark_direct[i] == -1)
							break;
						int direct = list.get(i)[shark_direct[i]][j];
						if (direct == 1) {
							if (x - 1 >= 0) {
								if (map[x - 1][y].ls.containsKey(i)) { // 해당 위치의 상어 냄새가 있을때
									if (result - map[x - 1][y].ls.get(i) <= R) { // 그 냄새가 아직 남아있는 경우
										map[x][y].shark = -1;
										if (map[x - 1][y].shark > i || map[x - 1][y].shark == -1) {
											map[x - 1][y].shark = i;
											map[x - 1][y].ls.put(i, result);
											shark[i].x = x - 1;
											shark[i].y = y;
											shark_direct[i] = 1;
										} else {
											shark[i].x = -1;
											shark[i].y = -1;
											shark_direct[i] = -1;
											count++; // 제거된 상어 카운트 올리기
										}
										break;
									}
								}

							}
						} else if (direct == 2) {
							if (x + 1 < N) {
								if (map[x + 1][y].ls.containsKey(i)) { // 해당 위치의 상어 냄새가 있을때
									if (result - map[x + 1][y].ls.get(i) <= R) { // 그 냄새가 아직 남아있는 경우
										map[x][y].shark = -1;
										if (map[x + 1][y].shark > i || map[x + 1][y].shark == -1) {
											map[x + 1][y].shark = i;
											map[x + 1][y].ls.put(i, result);
											shark[i].x = x + 1;
											shark[i].y = y;
											shark_direct[i] = 2;
										} else {
											shark[i].x = -1;
											shark[i].y = -1;
											shark_direct[i] = -1;
											count++; // 제거된 상어 카운트 올리기
										}
										break;
									}
								}

							}
						} else if (direct == 3) {
							if (y - 1 >= 0) {
								if (map[x][y - 1].ls.containsKey(i)) { // 해당 위치의 상어 냄새가 있을때
									if (result - map[x][y - 1].ls.get(i) <= R) { // 그 냄새가 아직 남아있는 경우
										map[x][y].shark = -1;
										if (map[x][y - 1].shark > i || map[x][y - 1].shark == -1) {
											map[x][y - 1].shark = i;
											map[x][y - 1].ls.put(i, result);
											shark[i].x = x;
											shark[i].y = y - 1;
											shark_direct[i] = 3;
										} else {
											shark[i].x = -1;
											shark[i].y = -1;
											shark_direct[i] = -1;
											count++; // 제거된 상어 카운트 올리기
										}
										break;
									}
								}

							}
						} else {
							if (y + 1 < N) {
								if (map[x][y + 1].ls.containsKey(i)) { // 해당 위치의 상어 냄새가 있을때
									if (result - map[x][y + 1].ls.get(i) <= R) { // 그 냄새가 아직 남아있는 경우
										map[x][y].shark = -1;
										if (map[x][y + 1].shark > i || map[x][y + 1].shark == -1) {
											map[x][y + 1].shark = i;
											map[x][y + 1].ls.put(i, result);
											shark[i].x = x;
											shark[i].y = y + 1;
											shark_direct[i] = 4;
										} else {
											shark[i].x = -1;
											shark[i].y = -1;
											shark_direct[i] = -1;
											count++; // 제거된 상어 카운트 올리기
										}
										break;
									}
								}

							}
						}
					}
				}
				// 냄새찾기종료
			}
			if (count == M - 1) // 1번제외 모두 제거된 경우 종료
				break;
			for (int i = 0; i < N; i++) { // 시간지난 냄새 다 지우기
				for (int j = 0; j < N; j++) {
					if (map[i][j].ls.size() == 0)
						continue;
					for (int k = 1; k <= M; k++) {
						if (map[i][j].ls.containsKey(k) && result - map[i][j].ls.get(k) >= R) { // 현재시간 - 냄새를 남긴 시간 >=
																								// 유효기간
							map[i][j].ls.remove(k);
						}
					}
				}
			}
			result++; // 시간 증가
		}

		if (result == 1001)
			result = -1;
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class Node {
		int shark;
		LinkedHashMap<Integer, Integer> ls = new LinkedHashMap<>();

		Node(int shark) {
			this.shark = shark;
		}
	}

	static class Loc {
		int x, y;

		Loc(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

//그림5에서  2번상어가 -1 -1 됨 원래는 1,3 이되야함