import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static List<List<Integer>> tree;
    static boolean[] visit;
    static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) tree.add(new ArrayList<>());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        visit = new boolean[N + 1];
        size = new int[N + 1];
        dfs(root);

        while (Q-- > 0) {
            int cmd = Integer.parseInt(br.readLine());
            sb.append(size[cmd]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int dfs(int vertex) {
        visit[vertex] = true;

        int cnt = 1;
        for (int child : tree.get(vertex)) {
            if (visit[child]) continue;
            cnt += dfs(child);
        }
        size[vertex] = cnt;

        return size[vertex];
    }

}

