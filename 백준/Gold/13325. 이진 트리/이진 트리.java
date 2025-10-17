import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, N + 1);
        int[] cost = new int[size];
        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 2; i < size; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            sum += cost[i];
        }

        for (int i = size / 2 - 1; i >= 1; i--) {
            int left = cost[i * 2];
            int right = cost[i * 2 + 1];
            sum += Math.abs(left - right);
            cost[i] += Math.max(left, right);
        }
        sb.append(sum);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

