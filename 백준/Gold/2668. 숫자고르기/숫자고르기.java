import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static List<Integer> graph;

    static boolean[] visit;
    static int N;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();

        graph.add(0);
        for (int i = 1; i <= N; i++) {
            graph.add(Integer.parseInt(br.readLine().trim()));
        }

        result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            visit = new boolean[N + 1];
            if (dfs(i, i)) result.add(i);
        }
        sb.append(result.size()).append("\n");
        for (int num : result) {
            sb.append(num).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean dfs(int start, int cur) {

        if (visit[cur] && start == cur) {
            return true;
        }
        if (visit[cur]) return false;
        visit[cur] = true;

        boolean check = dfs(start, graph.get(cur));
        return check;
    }
}
