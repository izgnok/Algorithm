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
        int K = Integer.parseInt(st.nextToken());

        int[] list = new int[N + 1];
        for(int i=1; i<=N; i++) {
            list[i] = Integer.parseInt(br.readLine().trim());
        }
        int[][] DP = new int[N + 1][K + 1];

        for(int i=0; i<=N; i++) {
            for(int j=0; j<=K; j++) {
                if(i == 0) {
                    DP[0][j] = 0;
                    continue;
                }
                if(j == 0) {
                    DP[i][0] = 1;
                    continue;
                }
                DP[i][j] = DP[i-1][j];
                if(j >= list[i]) DP[i][j] += DP[i][j-list[i]];
            }
        }
        sb.append(DP[N][K]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}