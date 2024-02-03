import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int Result = Integer.MAX_VALUE;
	static Food[] food;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		food = new Food[N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			Food input = new Food(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			food[i] = input;
		}
		for(int i=0; i<N; i++) {
			dfs(i,food[i].x, food[i].y);
		}
		
		sb.append(Result + "");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	static void dfs(int depth, int X, int Y) {
		if(depth == N) {
			return;
		}
		if(Result > Math.abs(X-Y)) Result = Math.abs(X-Y);
		
		for(int i= depth+1; i<N; i++) {
			dfs(i, X * food[i].x, Y + food[i].y);
		}
		
	}
	
	static class Food {
		int x, y;
		
		Food(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}