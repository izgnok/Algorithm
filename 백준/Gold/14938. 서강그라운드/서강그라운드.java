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
        int E = Integer.parseInt(st.nextToken());

        int[] item = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        int INF = 987654321;
        int[][] cost = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i==j) cost[i][j] = 0;
                else cost[i][j] = INF;
            }
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cost[x][y] = c;
            cost[y][x] = c;
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                }
            }
        }

        int answer = 0;
        for(int i=1; i<=N; i++) {
            int sum = 0;
            for(int j=1; j<=N; j++) {
                if(cost[i][j] <= M) sum += item[j];
            }
            answer = Math.max(answer, sum);
        }

        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}