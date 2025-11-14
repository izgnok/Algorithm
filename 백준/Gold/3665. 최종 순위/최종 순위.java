import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static int N;
    static boolean[][] graph;
    static int[] inDegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {

            N = Integer.parseInt(br.readLine());
            graph = new boolean[N + 1][N + 1];
            inDegree = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                set.add(num);
                for (int j = 1; j <= N; j++) {
                    if (set.contains(j)) continue;
                    graph[num][j] = true;
                    inDegree[j]++;
                }
            }

            int M = Integer.parseInt(br.readLine());
            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (graph[a][b]) {
                    graph[a][b] = false;
                    graph[b][a] = true;
                    inDegree[a]++;
                    inDegree[b]--;
                } else {
                    graph[b][a] = false;
                    graph[a][b] = true;
                    inDegree[a]--;
                    inDegree[b]++;
                }
            }
            sb.append(bfs()).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static String bfs() {
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] != 0) continue;
            q.add(i);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {

            if (q.isEmpty()) return "IMPOSSIBLE";
            if (q.size() > 1) return "?";

            int cur = q.poll();
            result.add(cur);

            for (int j = 1; j <= N; j++) {
                if (!graph[cur][j]) continue;

                inDegree[j]--;
                if (inDegree[j] == 0) q.add(j);
            }
        }
        String str = "";
        for (int rs : result) str += rs + " ";
        return str;
    }
}