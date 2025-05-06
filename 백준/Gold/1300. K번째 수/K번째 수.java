

import java.io.*;
import java.util.*;

public class Main {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        K = Integer.parseInt(br.readLine().trim());

        int start = 1;
        int end = K;
        while (start <= end) {
            int mid = (start + end) / 2;

            int count = 0;
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }
            if (count < K) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        sb.append(start);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
