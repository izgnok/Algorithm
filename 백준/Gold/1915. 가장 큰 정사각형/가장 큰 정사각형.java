import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        int[][] DP = new int[N][M];
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    DP[i][j] = 0;
                    continue;
                }
                if (i == 0) DP[i][j] = 1;
                else if (j == 0) DP[i][j] = 1;
                else DP[i][j] = Math.min(Math.min(DP[i - 1][j], DP[i][j - 1]), DP[i - 1][j - 1]) + 1;
                result = Math.max(result, DP[i][j]);
            }
        }
        sb.append(result * result).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}