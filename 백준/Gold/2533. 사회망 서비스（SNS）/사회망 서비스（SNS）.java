import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static List<List<Integer>> tree;

    static int[] DP1;
    static int[] DP2;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) tree.add(new ArrayList<>());

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        DP1 = new int[N + 1];
        DP2 = new int[N + 1];
        visit = new boolean[N + 1];
        dfs(1);

        sb.append(Math.min(DP1[1], DP2[1]));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int num) {
        visit[num] = true;
        DP1[num] = 1;
        DP2[num] = 0;

        for (int child : tree.get(num)) {
            if (visit[child]) continue;
            dfs(child);
            DP1[num] += Math.min(DP1[child], DP2[child]);
            DP2[num] += DP1[child];
        }
    }
}

