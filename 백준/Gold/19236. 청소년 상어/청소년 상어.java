import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		loc[] init_shark = new loc[18];
		Node[][] init_map = new Node[4][4];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int shark_num = Integer.parseInt(st.nextToken());
				int shark_direct = Integer.parseInt(st.nextToken());
				if (i == 0 && j == 0) {
					result += shark_num;
					init_shark[shark_num] = new loc(-1, -1);
					shark_num = 17; // 상어의 번호를 17로
				}
				init_map[i][j] = new Node(shark_num, shark_direct);
				init_shark[shark_num] = new loc(i, j);
			}
		}

		dfs(result, init_map, init_shark);
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int cur, Node[][] cur_map, loc[] cur_shark) {
		// 현재 상태 복사
		Node[][] map = new Node[4][4];
		loc[] shark = new loc[18];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int direct = cur_map[i][j].direct;
				int num = cur_map[i][j].num;
				map[i][j] = new Node(num,direct);
			}
		}
		for (int i = 1; i < 18; i++) {
			int x = cur_shark[i].x;
			int y = cur_shark[i].y;
			shark[i] = new loc(x,y);
		}

		// 물고기 대이동
		for (int i = 1; i <= 16; i++) {
			int x = shark[i].x;
			int y = shark[i].y;
			if (x == -1 || y == -1)
				continue;
			if (map[x][y].num == -1)
				continue; // 빈칸이면 패스
			int direct = map[x][y].direct;

			int row, col;
			int count = 8;
			while (count > 0) {
				if(direct > 8) direct = 1;
				if (direct == 1) { // 상
					row = x - 1;
					col = y;
					if (row >= 0 && row < 4 && col >= 0 && col < 4 && map[row][col].num != 17) {
						int tmp_num = map[row][col].num;
						int tmp_direct = map[row][col].direct;
						shark[i].x = row; // 현재 물고기 좌표 업데이트
						shark[i].y = col;
						if (tmp_num != -1) {
							shark[tmp_num].x = x; // 상대 물고기 좌표 업데이트
							shark[tmp_num].y = y;
						}
						map[row][col].num = i; // 맵 업데이트
						map[row][col].direct = direct;
						map[x][y].num = tmp_num;
						map[x][y].direct = tmp_direct;
						break;
					} else {
						direct++;
					}
				} else if (direct == 2) { // 좌상
					row = x - 1;
					col = y - 1;
					if (row >= 0 && row < 4 && col >= 0 && col < 4 && map[row][col].num != 17) {
						int tmp_num = map[row][col].num;
						int tmp_direct = map[row][col].direct;
						shark[i].x = row; // 현재 물고기 좌표 업데이트
						shark[i].y = col;
						if (tmp_num != -1) {
							shark[tmp_num].x = x; // 상대 물고기 좌표 업데이트
							shark[tmp_num].y = y;
						}
						map[row][col].num = i; // 맵 업데이트
						map[row][col].direct = direct;
						map[x][y].num = tmp_num;
						map[x][y].direct = tmp_direct;
						break;
					} else {
						direct++;
					}
				} else if (direct == 3) { // 좌
					row = x;
					col = y - 1;
					if (row >= 0 && row < 4 && col >= 0 && col < 4 && map[row][col].num != 17) {
						int tmp_num = map[row][col].num;
						int tmp_direct = map[row][col].direct;
						shark[i].x = row; // 현재 물고기 좌표 업데이트
						shark[i].y = col;
						if (tmp_num != -1) {
							shark[tmp_num].x = x; // 상대 물고기 좌표 업데이트
							shark[tmp_num].y = y;
						}

						map[row][col].num = i; // 맵 업데이트
						map[row][col].direct = direct;
						map[x][y].num = tmp_num;
						map[x][y].direct = tmp_direct;
						break;
					} else {
						direct++;
					}
				} else if (direct == 4) { // 좌하
					row = x + 1;
					col = y - 1;
					if (row >= 0 && row < 4 && col >= 0 && col < 4 && map[row][col].num != 17) {
						int tmp_num = map[row][col].num;
						int tmp_direct = map[row][col].direct;
						shark[i].x = row; // 현재 물고기 좌표 업데이트
						shark[i].y = col;
						if (tmp_num != -1) {
							shark[tmp_num].x = x; // 상대 물고기 좌표 업데이트
							shark[tmp_num].y = y;
						}
						map[row][col].num = i; // 맵 업데이트
						map[row][col].direct = direct;
						map[x][y].num = tmp_num;
						map[x][y].direct = tmp_direct;
						break;
					} else {
						direct++;
					}
				} else if (direct == 5) { // 하
					row = x + 1;
					col = y;
					if (row >= 0 && row < 4 && col >= 0 && col < 4 && map[row][col].num != 17) {
						int tmp_num = map[row][col].num;
						int tmp_direct = map[row][col].direct;
						shark[i].x = row; // 현재 물고기 좌표 업데이트
						shark[i].y = col;
						if (tmp_num != -1) {
							shark[tmp_num].x = x; // 상대 물고기 좌표 업데이트
							shark[tmp_num].y = y;
						}
						map[row][col].num = i; // 맵 업데이트
						map[row][col].direct = direct;
						map[x][y].num = tmp_num;
						map[x][y].direct = tmp_direct;
						break;
					} else {
						direct++;
					}
				} else if (direct == 6) { // 우하
					row = x + 1;
					col = y + 1;
					if (row >= 0 && row < 4 && col >= 0 && col < 4 && map[row][col].num != 17) {
						int tmp_num = map[row][col].num;
						int tmp_direct = map[row][col].direct;
						shark[i].x = row; // 현재 물고기 좌표 업데이트
						shark[i].y = col;
						if (tmp_num != -1) {
							shark[tmp_num].x = x; // 상대 물고기 좌표 업데이트
							shark[tmp_num].y = y;
						}
						map[row][col].num = i; // 맵 업데이트
						map[row][col].direct = direct;
						map[x][y].num = tmp_num;
						map[x][y].direct = tmp_direct;
						break;
					} else {
						direct++;
					}
				} else if (direct == 7) { // 우
					row = x;
					col = y + 1;
					if (row >= 0 && row < 4 && col >= 0 && col < 4 && map[row][col].num != 17) {
						int tmp_num = map[row][col].num;
						int tmp_direct = map[row][col].direct;
						shark[i].x = row; // 현재 물고기 좌표 업데이트
						shark[i].y = col;
						if (tmp_num != -1) {
							shark[tmp_num].x = x; // 상대 물고기 좌표 업데이트
							shark[tmp_num].y = y;
						}
						map[row][col].num = i; // 맵 업데이트
						map[row][col].direct = direct;
						map[x][y].num = tmp_num;
						map[x][y].direct = tmp_direct;
						break;
					} else {
						direct++;
					}
				} else { // 우상
					row = x - 1;
					col = y + 1;
					if (row >= 0 && row < 4 && col >= 0 && col < 4 && map[row][col].num != 17) {
						int tmp_num = map[row][col].num;
						int tmp_direct = map[row][col].direct;
						shark[i].x = row; // 현재 물고기 좌표 업데이트
						shark[i].y = col;
						if (tmp_num != -1) {
							shark[tmp_num].x = x; // 상대 물고기 좌표 업데이트
							shark[tmp_num].y = y;
						}
						map[row][col].num = i; // 맵 업데이트
						map[row][col].direct = direct;
						map[x][y].num = tmp_num;
						map[x][y].direct = tmp_direct;
						break;
					} else {
						direct++;
					}
				}
				count--;
			}
		}

		// 상어 먹이 경우의수
		int x = shark[17].x;
		int y = shark[17].y;
		int direct = map[x][y].direct;

		List<loc> feed = new ArrayList<>();
		if (direct == 1) {
			x--;
			while (x >= 0) {
				if (map[x][y].num != -1) {
					feed.add(new loc(x, y));
				}
				x--;
			}
		} else if (direct == 2) {
			x--;
			y--;
			while (x >= 0 && y >= 0) {
				if (map[x][y].num != -1) {
					feed.add(new loc(x, y));
				}
				x--;
				y--;
			}
		} else if (direct == 3) {
			y--;
			while (y >= 0) {
				if (map[x][y].num != -1) {
					feed.add(new loc(x, y));
				}
				y--;
			}
		} else if (direct == 4) {
			x++;
			y--;
			while (x < 4 && y >= 0) {
				if (map[x][y].num != -1) {
					feed.add(new loc(x, y));
				}
				x++;
				y--;
			}
		} else if (direct == 5) {
			x++;
			while (x < 4) {
				if (map[x][y].num != -1) {
					feed.add(new loc(x, y));
				}
				x++;
			}
		} else if (direct == 6) {
			x++;
			y++;
			while (x < 4 && y < 4) {
				if (map[x][y].num != -1) {
					feed.add(new loc(x, y));
				}
				x++;
				y++;
			}
		} else if (direct == 7) {
			y++;
			while (y < 4) {
				if (map[x][y].num != -1) {
					feed.add(new loc(x, y));
				}
				y++;
			}
		} else {
			x--;
			y++;
			while (x >= 0 && y < 4) {
				if (map[x][y].num != -1) {
					feed.add(new loc(x, y));
				}
				x--;
				y++;
			}
		}
		
		for (int i = 0; i < feed.size(); i++) {
			x = feed.get(i).x;
			y = feed.get(i).y;
			int feed_num = map[x][y].num;
			int shark_x = shark[17].x;
			int shark_y = shark[17].y;
			int shark_direct = map[shark_x][shark_y].direct;

			// 먹이 점수 업데이트
			cur += feed_num;
			// 지도 업데이트
			map[shark_x][shark_y].num = -1;
			map[shark_x][shark_y].direct = -1;
			map[x][y].num = 17;
			// 상어 좌표 업데이트
			shark[17].x = x;
			shark[17].y = y;
			shark[feed_num].x = -1;
			shark[feed_num].y = -1;

			// 탐색
			dfs(cur, map, shark);

			// 복구
			cur -= feed_num;
			map[shark_x][shark_y].num = 17;
			map[shark_x][shark_y].direct = shark_direct;
			map[x][y].num = feed_num;
			shark[17].x = shark_x;
			shark[17].y = shark_y;
			shark[feed_num].x = x;
			shark[feed_num].y = y;
		}

		if (result < cur)
			result = cur;
		return;
	}

	static class Node { // 번호, 방향 저장하기 위한 객체
		int num, direct;

		Node(int num, int direct) {
			this.num = num;
			this.direct = direct;
		}
	}

	static class loc { // 좌표를 저장하기 위한 객체
		int x, y;

		loc(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}