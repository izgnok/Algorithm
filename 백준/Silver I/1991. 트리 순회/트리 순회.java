import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static int N;
	static List<List<Character>> tree;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		tree = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			tree.add(new ArrayList<>());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char parent = st.nextToken().charAt(0);
			for (int j = 0; j < 2; j++) {
				char child = st.nextToken().charAt(0);
				tree.get(parent - 'A').add(child);
			}
		}

		front(0);
		sb.append("\n");
		mid(0);
		sb.append("\n");
		back(0);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void front(int idx) {
		sb.append((char)(idx + 'A'));

		if (tree.get(idx).get(0) != '.') {
			front(tree.get(idx).get(0) - 'A');
		}
		if (tree.get(idx).get(1) != '.') {
			front(tree.get(idx).get(1) - 'A');
		}
		return;
	}

	static void back(int idx) {
		if (tree.get(idx).get(0) != '.') {
			back(tree.get(idx).get(0) - 'A');
		}
		if (tree.get(idx).get(1) != '.') {
			back(tree.get(idx).get(1) - 'A');
		}
		sb.append((char)(idx + 'A'));
	}

	static void mid(int idx) {
		if (tree.get(idx).get(0) != '.') {
			mid(tree.get(idx).get(0) - 'A');
		}
		sb.append((char)(idx + 'A'));
		if (tree.get(idx).get(1) != '.') {
			mid(tree.get(idx).get(1) - 'A');
		}
	}
}