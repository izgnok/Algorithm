import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<List<Integer>> graph;

    static int[] inDegree, outDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        inDegree = new int[N + 1];
        outDegree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] >= (N + 1) / 2 || outDegree[i] >= (N + 1) / 2) count++;
        }
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[N + 1];
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : graph.get(cur)) {
                if (visit[next]) continue;
                visit[next] = true;
                outDegree[start]++;
                inDegree[next]++;
                q.add(next);
            }
        }
    }

}
