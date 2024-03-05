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

public class Main {
	static int[][] map = new int[9][9];
	static int[][] copy = new int[9][9];
	static boolean success;
	static List<Node> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		list = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			String str = br.readLine().trim();
			for (int j = 0; j < 9; j++) {
				map[i][j] = str.charAt(j) - '0';
				copy[i][j] = map[i][j];
				if (map[i][j] == 0) {
					list.add(new Node(i, j));
				}
			}
		}

		success = false;
		dfs(0);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void dfs(int depth) {
		if (depth == list.size()) {
			for(int i=0; i<9; i++) {
				map[i] = copy[i].clone();
			}
			success = true;
			return;
		}
		Node node = list.get(depth);
		int x = node.x;
		int y = node.y;
		for (int i = 1; i <= 9; i++) {
			if (success) {
				return;
			}
			if (possilbe(i, x, y)) {
				copy[x][y] = i;
				dfs(depth + 1);
				copy[x][y] = 0;
			}
		}
	}

	static boolean possilbe(int num, int x, int y) {
		// 세로체크
		for (int i = x - 1; i >= 0; i--) {
			if (num == copy[i][y]) {
				return false;
			}
		}
		for (int i = x + 1; i < 9; i++) {
			if (num == copy[i][y]) {
				return false;
			}
		}

		// 가로체크
		for (int j = y - 1; j >= 0; j--) {
			if (num == copy[x][j]) {
				return false;
			}
		}
		for (int j = y + 1; j < 9; j++) {
			if (num == copy[x][j]) {
				return false;
			}
		}

		// 사각형체크

		if (y % 3 == 0) {
			if (x % 3 == 0) {
				for (int i = x; i < x + 3; i++) {
					for (int j = y; j < y + 3; j++) {
						if (copy[i][j] == num)
							return false;
					}
				}
			}
			if (x % 3 == 1) {
				for (int i = x - 1; i < x + 2; i++) {
					for (int j = y; j < y + 3; j++) {
						if (copy[i][j] == num)
							return false;
					}
				}
			}
			if (x % 3 == 2) {
				for (int i = x - 2; i < x + 1; i++) {
					for (int j = y; j < y + 3; j++) {
						if (copy[i][j] == num)
							return false;
					}
				}
			}
		}

		if (y % 3 == 1) {
			if (x % 3 == 0) {
				for (int i = x; i < x + 3; i++) {
					for (int j = y - 1; j < y + 2; j++) {
						if (copy[i][j] == num)
							return false;
					}
				}
			}
			if (x % 3 == 1) {
				for (int i = x - 1; i < x + 2; i++) {
					for (int j = y - 1; j < y + 2; j++) {
						if (copy[i][j] == num)
							return false;
					}
				}
			}
			if (x % 3 == 2) {
				for (int i = x - 2; i < x + 1; i++) {
					for (int j = y - 1; j < y + 2; j++) {
						if (copy[i][j] == num)
							return false;
					}
				}
			}
		}

		if (y % 3 == 2) {
			if (x % 3 == 0) {
				for (int i = x; i < x + 3; i++) {
					for (int j = y - 2; j < y + 1; j++) {
						if (copy[i][j] == num)
							return false;
					}
				}
			}
			if (x % 3 == 1) {
				for (int i = x - 1; i < x + 2; i++) {
					for (int j = y - 2; j < y + 1; j++) {
						if (copy[i][j] == num)
							return false;
					}
				}
			}
			if (x % 3 == 2) {
				for (int i = x - 2; i < x + 1; i++) {
					for (int j = y - 2; j < y + 1; j++) {
						if (copy[i][j] == num)
							return false;
					}
				}
			}
		}

		return true;
	}

	static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}