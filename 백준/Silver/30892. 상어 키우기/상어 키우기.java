
import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (arr[i] >= T) {
                if (!dq.isEmpty() && K > 0) {
                    T += dq.pollLast();
                    K--;
                } else break;
            }
            if (arr[i] < T) {
                dq.addLast(arr[i]);
            }
        }
        while (K > 0 && !dq.isEmpty()) {
            T += dq.pollLast();
            K--;
        }
        sb.append(T);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
