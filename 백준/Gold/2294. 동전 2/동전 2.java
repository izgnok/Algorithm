import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] DP = new int[M + 1];
        int INF = 987654321;
        Arrays.fill(DP, INF);
        DP[0] = 0;

        while (N-- > 0) {
            int cost = Integer.parseInt(br.readLine());
            for (int i = cost; i <= M; i++) {
                DP[i] = Math.min(DP[i], DP[i - cost] + 1);
            }
        }

        sb.append(DP[M] == INF ? -1 : DP[M]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}