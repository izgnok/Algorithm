import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int check = Integer.parseInt(st.nextToken());
                if (check == 1) {
                    graph.get(i + 1).add(j + 1);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = 0;
        boolean check = true;
        for (int i = 0; i < M; i++) {
            int end = Integer.parseInt(st.nextToken());
            if (start != 0 && start != end) check = bfs(start, end);
            if (!check) break;
            start = end;
        }
        if (check) sb.append("YES");
        else sb.append("NO");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean bfs(int start, int end) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[N + 1];
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            for (int child : graph.get(q.poll())) {
                if (child == end) return true;
                if (visit[child]) continue;
                q.add(child);
                visit[child] = true;
            }
        }
        return false;
    }

}
