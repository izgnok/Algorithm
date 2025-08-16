import java.util.*;
import java.io.*;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (parent[i] == i) result.add(i);
        }

        sb.append(result.get(0)).append(" ").append(result.get(1));
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
    }

}