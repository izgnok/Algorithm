import java.util.*;
import java.io.*;

public class Main {

    static int[] parent;

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        int result = 0;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            boolean check = union(a, b);
            if (check) {
                result = m + 1;
                break;
            }
        }
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        parent[b] = a;
        if (a == b) return true;
        return false;
    }
}