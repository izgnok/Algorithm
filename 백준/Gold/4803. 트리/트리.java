import java.util.*;
import java.io.*;

public class Main {

    static int[] parent;
    static boolean[] cycle;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int test_case = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;

            parent = new int[N + 1];
            cycle = new boolean[N + 1];
            for (int i = 1; i <= N; i++) parent[i] = i;

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            int result = 0;
            for (int i = 1; i <= N; i++) {
                if (parent[i] == i && !cycle[i]) result++;
            }
            sb.append("Case ").append(test_case++).append(": ");
            if (result > 1) sb.append("A forest of ").append(result).append(" trees.");
            else if (result == 1) sb.append("There is one tree.");
            else sb.append("No trees.");
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        parent[b] = a;

        if (a == b) cycle[a] = true;
        if (cycle[b]) cycle[a] = true;
    }
}