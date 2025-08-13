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

        parent = new int[N * M];
        for (int i = 0; i < N * M; i++) parent[i] = i;

        for (int i = 0; i < N; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                char c = str.charAt(j);
                int a = (i * M) + j;
                int b = (i * M) + j;
                if (c == 'D') b += M;
                else if (c == 'U') b -= M;
                else if (c == 'L') b--;
                else b++;
                union(a, b);
            }
        }

        int result = 0;
        for (int i = 0; i < N * M; i++) {
            if (find(i) != i) continue;
            result++;
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

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        parent[b] = a;
    }
}