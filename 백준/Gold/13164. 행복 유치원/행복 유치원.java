import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N - 1];
        st = new StringTokenizer(br.readLine());
        int pre = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N - 1; i++) {
            int cur = Integer.parseInt(st.nextToken());
            arr[i] = cur - pre;
            pre = cur;
        }
        Arrays.sort(arr);

        int result = 0;
        for (int i = 0; i < N - K; i++) result += arr[i];

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}