import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	private static class Point {
		public int x;
		public int y;
		public int distance;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.distance = Math.abs(x - 0) + Math.abs(y - 0);  // 맨해튼 거리
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int N = Integer.parseInt(in.readLine()); 
			Point[] points = new Point[N];

			int odd = 0;
			int even = 0;
			int max = 0; 

			for (int i = 0; i < N; i++) {
				
				String[] split = in.readLine().split(" ");
				int x = Integer.parseInt(split[0]);
				int y = Integer.parseInt(split[1]);
				points[i] = new Point(x, y);

				if (max < points[i].distance) {
					max = points[i].distance;
				}

				if (points[i].distance % 2 == 0) {
					even++;
				}
				else {
					odd++;
				}
			}
	
			if (odd != 0 && even != 0) {
				sb.append(-1).append("\n");
				continue;  
			}

			int acc = 0;  
			int i = 0; 
			while (true) {
				acc += i;

				if (acc >= max && (acc - max) % 2 == 0) {
					sb.append(i).append("\n");
					break;
				}
				i++;
			}
		}
		System.out.println(sb);
	}

}