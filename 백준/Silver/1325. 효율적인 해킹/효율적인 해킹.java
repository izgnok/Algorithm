import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<List<Integer>> graph;
    static int[] visit;
    static int visitToken = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(b).add(a);
        }

        visit = new int[N + 1];
        int max = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int cnt = bfs(i);
            if (cnt > max) {
                max = cnt;
                result.clear();
                result.add(i);
            } else if (cnt == max) {
                result.add(i);
            }
            visitToken++;
            if (visitToken == Integer.MAX_VALUE - 5) {
                Arrays.fill(visit, 0);
                visitToken = 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        Collections.sort(result);
        for (int x : result) sb.append(x).append(' ');
        System.out.println(sb.toString().trim());
    }

    static int bfs(int start) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visit[start] = visitToken;
        int cnt = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            cnt++;
            for (int child : graph.get(cur)) {
                if (visit[child] == visitToken) continue;
                visit[child] = visitToken;
                q.add(child);
            }
        }
        return cnt;
    }
}