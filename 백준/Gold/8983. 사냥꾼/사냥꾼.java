import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int[] arr;
    static int N, M, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        int result = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int start = 0;
            int end = N - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                int dist = Math.abs(x - arr[mid]) + y;

                if (dist <= L) {
                    result++;
                    break;
                } else {
                    if (x < arr[mid]) end = mid - 1;
                    else if (x > arr[mid]) start = mid + 1;
                    else break;
                }
            }

        }
        sb.append(result).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}