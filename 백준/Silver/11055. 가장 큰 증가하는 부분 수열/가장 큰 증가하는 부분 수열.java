import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] DP = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            DP[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) DP[i] = Math.max(DP[i], DP[j] + arr[i]);
            }
            max = Math.max(max, DP[i]);
        }

        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}