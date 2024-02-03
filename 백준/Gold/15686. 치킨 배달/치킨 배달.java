import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static int result = Integer.MAX_VALUE;
	static int N, M;
	static List<City> home = new ArrayList<>();
	static List<City> chicken = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int k = Integer.parseInt(st.nextToken());
				if (k == 1) {
					City tmp = new City(i, j);
					home.add(tmp);
				}
				if (k == 2) {
					City tmp = new City(i, j);
					chicken.add(tmp);
				}
			}
		}

		for (int i = 0; i < home.size(); i++) {
			for (int j = 0; j < chicken.size(); j++) {
				int home_x = home.get(i).x;
				int home_y = home.get(i).y;
				int chicken_x = chicken.get(j).x;
				int chicken_y = chicken.get(j).y;
				int dist = Math.abs(home_x - chicken_x) + Math.abs(home_y - chicken_y);
				home.get(i).dist.add(dist); // 치킨집개수만큼 거리배열만들어짐
			}
		}

		for (int i = 0; i <= chicken.size() - M; i++) {
			int[] min_dist = new int[home.size()];
			back_track(i, 1, min_dist);
		}

		sb.append(String.valueOf(result));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static class City {
		int x, y;
		List<Integer> dist = new ArrayList<>();

		City(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void back_track(int idx, int m, int[] min_dist) {
		int cur = 0;
		int[] dist = new int[min_dist.length];
		for (int i = 0; i < dist.length; i++) {
			dist[i] = min_dist[i];
		}
		for (int i = 0; i < home.size(); i++) {
			if (m == 1)
				dist[i] = Integer.MAX_VALUE;
			if (dist[i] > home.get(i).dist.get(idx)) {
				cur += home.get(i).dist.get(idx);
			} else
				cur += dist[i];
		}

		for (int i = 0; i < home.size(); i++) {
			if (dist[i] > home.get(i).dist.get(idx)) {
				dist[i] = home.get(i).dist.get(idx);
			}
		}

		if (m == M) {
			if (result > cur)
				result = cur;
			return;
		}
		for (int i = idx + 1; i < chicken.size(); i++) {
			back_track(i, m + 1, dist);
		}
	}
}