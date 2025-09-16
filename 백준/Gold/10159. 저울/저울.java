import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<List<Integer>> graph;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        count = new int[N + 1];
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        for (int i = 1; i <= N; i++) bfs(i);
        for (int i = 1; i <= N; i++) sb.append(N - count[i] - 1).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        boolean[] visit = new boolean[N + 1];
        visit[start] = true;

        while (!q.isEmpty()) {
            for (int child : graph.get(q.poll())) {
                if (visit[child]) continue;
                visit[child] = true;
                q.add(child);
                count[child]++;
                count[start]++;
            }
        }
    }
}