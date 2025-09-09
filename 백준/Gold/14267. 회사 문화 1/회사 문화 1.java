import java.io.*;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] parent = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) parent[i] = Integer.parseInt(st.nextToken());

        long[] arr = new long[N + 1];
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            arr[num] += score;
        }

        for (int i = 2; i <= N; i++) arr[i] += arr[parent[i]];
        for (int i = 1; i <= N; i++) sb.append(arr[i]).append(" ");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}