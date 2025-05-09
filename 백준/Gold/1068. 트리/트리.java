import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N;

    static List<List<Integer>> graph;
    static int root;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a == -1) {
                root = i;
                continue;
            }
            graph.get(a).add(i);
        }

        boolean[] visit = new boolean[N + 1];
        int death = Integer.parseInt(br.readLine().trim());
        visit[root] = true;
        visit[death] = true;
        count = 0;
        if (root != death) dfs(root, visit);
        visit[root] = false;
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int vertex, boolean[] visit) {

        int childCount = 0;
        for (int child : graph.get(vertex)) {
            if (visit[child]) continue;
            childCount++;
            visit[child] = true;
            dfs(child, visit);
            visit[child] = false;
        }

        if (childCount == 0) count++;
    }
}
