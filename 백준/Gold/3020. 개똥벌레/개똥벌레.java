

import java.io.*;
import java.util.*;

public class Main {

    static int N, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());


        int[] suk = new int[H + 1];
        int[] jong = new int[H + 1];
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine().trim());
            if (i % 2 == 0) {
                suk[num]++;
            } else {
                jong[num]++;
            }
        }

        for (int i = H - 1; i >= 1; i--) {
            suk[i] += suk[i + 1];
            jong[i] += jong[i + 1];
        }


        int min = Integer.MAX_VALUE;
        int count = 1;
        for (int i = 1; i <= H; i++) {
            int cur = suk[i] + jong[H - i + 1];
            if (min > cur) {
                min = cur;
                count = 1;
            }
            else if (min == cur) {
                count++;
            }
        }
        sb.append(min).append(" ").append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
