import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (list.isEmpty()) {
				list.add(num);
			} else {
				int pre = list.get(list.size() - 1);
				if (pre >= num) {
					int idx = binarySearch(num);
					list.set(idx, num);
				} else {
					list.add(num);
				}
			}
		}
		sb.append(list.size());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int binarySearch(int next) {
		int start = 0;
		int end = list.size() - 1;

		while (start < end) {
			int mid = (start + end) / 2;
			
			if(list.get(mid) >= next) {
				end = mid;
			}
			else {
				start = mid+1;
			}
		}
		return end;
	}

}