import java.util.*;
import java.io.*;

public class Main {


    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());
        int M = Integer.parseInt(br.readLine().trim());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) parent[i] = i;

        int result = 0;
        for (int i = 0; i < M; i++) {
            int k = Integer.parseInt(br.readLine().trim());
            int x = find(k);
            if (x == 0) break;
            parent[x] = find(x - 1);
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
}