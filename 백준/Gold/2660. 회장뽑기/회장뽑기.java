import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int INF = 987654321;
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(cost[i], INF);
            cost[i][i] = 0;
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 || b == -1) break;
            cost[a][b] = 1;
            cost[b][a] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                if (i == k) continue;
                if (cost[i][k] == INF) continue;
                for (int j = 1; j <= N; j++) {
                    if (j == k) continue;
                    if (cost[k][j] == INF) continue;
                    cost[i][j] = Math.min(cost[i][k] + cost[k][j], cost[i][j]);
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        int min = INF;
        for (int i = 1; i <= N; i++) {
            int tmp = 0;
            for (int j = 1; j <= N; j++) {
                tmp = Math.max(tmp, cost[i][j]);
            }
            if (min > tmp) {
                list.clear();
                list.add(i);
                min = tmp;
            } else if (min == tmp) list.add(i);
        }

        sb.append(min).append(" ").append(list.size()).append("\n");
        for (int result : list) sb.append(result).append(" ");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}