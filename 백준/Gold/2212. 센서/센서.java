import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        if (N <= K) sb.append(0);
        else {
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr);

            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
            for (int i = 1; i < N; i++) pq.add(arr[i] - arr[i - 1]);
            int result = 0;
            while (K-- > 1) pq.poll();
            while (!pq.isEmpty()) result += pq.poll();
            sb.append(result);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}