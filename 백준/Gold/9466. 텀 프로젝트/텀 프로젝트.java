import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int[] check;
    static int[] arr;
    static int N;
    static boolean[] visit;
    static Deque<Integer> dq = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            check = new int[N + 1];
            visit = new boolean[N + 1];
            for (int i = 1; i <= N; i++) {
                if (visit[i]) continue;
                dq = new ArrayDeque<>();
                dfs(i);
            }

            int count = 0;
            for (int i = 1; i <= N; i++) {
                if (check[i] == -1) count++;
            }
            sb.append(count).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int cur) {
        dq.addLast(cur);
        if (visit[cur]) {
            while (dq.peekFirst() != cur) {
                if (check[dq.peekFirst()] == 0) check[dq.pollFirst()] = -1;
                else break;
            }
            while (!dq.isEmpty()) {
                if (check[dq.peekFirst()] == 0) check[dq.pollFirst()] = 1;
                else break;
            }
            return;
        }
        visit[cur] = true;
        dfs(arr[cur]);
    }
}
