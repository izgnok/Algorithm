import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] costs = new int[N + 1][N + 1];

        int INF = 987654321;
        for (int i = 1; i <= N; i++) {
            Arrays.fill(costs[i], INF);
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            costs[x][y] = cost;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (costs[i][k] == INF) continue;
                for (int j = 1; j <= N; j++) {
                    if (costs[k][j] == INF) continue;
                    if (costs[i][k] + costs[k][j] < costs[i][j]) {
                        costs[i][j] = costs[i][k] + costs[k][j];
                    }
                }
            }
        }

        int min = INF;
        for (int i = 1; i <= N; i++) {
            min = Math.min(min, costs[i][i]);
        }
        sb.append(min == INF ? "-1" : min);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

