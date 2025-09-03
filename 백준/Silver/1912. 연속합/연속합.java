import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] DP = new int[N];
        DP[0] = arr[0];
        int result = arr[0];
        
        for (int i = 1; i < N; i++) {
            DP[i] = Math.max(DP[i - 1] + arr[i], arr[i]);
            result = Math.max(DP[i], result);
        }
        sb.append(result).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}