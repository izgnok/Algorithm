import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int T, W;
    static int[][] DP;
    static int[] apple;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        DP = new int[T+1][W+1];
        apple = new int[T+1];

        for(int i=1; i<=T; i++) {
            apple[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=T; i++) {
            for(int j=0; j<=W; j++) {
                int k = apple[i];
                if(j==0) {
                    DP[i][j] = DP[i-1][0];
                    if(k == 1) DP[i][j]++;
                    continue;
                }

                DP[i][j] = Math.max(DP[i-1][j-1], DP[i-1][j]);
                if((j % 2 == 0 && k == 1) || (j % 2 == 1 && k == 2)) {
                    DP[i][j]++;
                }
            }
        }
        int max = 0;
        for(int i=0; i<=W; i++) {
            if(max < DP[T][i]) max = DP[T][i];
        }
        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}