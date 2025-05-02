import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] arr;
    static long[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        long min = Long.MAX_VALUE;
        long[] result = new long[3];
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = arr[i] + arr[left] + arr[right];
                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    result[0] = arr[i];
                    result[1] = arr[left];
                    result[2] = arr[right];
                }
                if (sum < 0) left++;
                else right--;
            }
        }
        for(long r: result) {
            sb.append(r).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
