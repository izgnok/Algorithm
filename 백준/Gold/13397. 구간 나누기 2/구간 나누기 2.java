import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = 9999;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (simulation(mid)) end = mid - 1;
            else start = mid + 1;
        }
        sb.append(end + 1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean simulation(int mid) {
        int cnt = 1;
        int min = arr[0];
        int max = arr[0];

        for (int i = 1; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if (max - min > mid) {
                cnt++;
                min = arr[i];
                max = arr[i];
            }
        }
        return cnt <= M;
    }
}