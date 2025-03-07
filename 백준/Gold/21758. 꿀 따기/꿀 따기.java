import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] sum = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (i > 0) sum[i] = sum[i - 1] + arr[i];
            else sum[i] = arr[i];
        }

        int result = getResult(N, sum, arr);
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int getResult(int N, int[] sum, int[] arr) {
        // 왼 -> 오 (벌통이 맨 오른쪽에 있는 경우)
        int result = 0;
        for (int i = 1; i < N - 1; i++) {
            int cur = (sum[N - 1] - arr[0] - arr[i]) + (sum[N - 1] - sum[i]);
            if (result < cur) result = cur;
        }
        // 오 -> 왼 (벌통이 맨 왼쪽에 있는 경우)
        for (int i = N - 2; i > 0; i--) {
            int cur = (sum[N - 1] - arr[N - 1] - arr[i]) + sum[i - 1];
            if (result < cur) result = cur;
        }
        // 양쪽에서 출발 (만나는 지점을 찾아야함)
        for (int i = 1; i < N - 1; i++) {
            int cur = (sum[i] - arr[0]) + (sum[N - 1] - sum[i - 1] - arr[N - 1]);
            if (result < cur) result = cur;
        }
        return result;
    }
}
