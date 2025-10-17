import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static List<List<Integer>> tree = new ArrayList<>();
    static int[] cost;

    static int[] DP1;
    static int[] DP2;
    static boolean[] visit;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) tree.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        cost = new int[N + 1];
        for (int i = 1; i <= N; i++) cost[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        DP1 = new int[N + 1];
        DP2 = new int[N + 1];
        dfs(1);

        visit = new boolean[N + 1];
        list = new ArrayList<>();
        trace(1, false);
        Collections.sort(list);

        sb.append(Math.max(DP1[1], DP2[1])).append("\n");
        for (int num : list) sb.append(num).append(" ");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void trace(int num, boolean pre) {
        visit[num] = true;

        boolean check = false;
        if (!pre && DP1[num] >= DP2[num]) {
            list.add(num);
            check = true;
        }

        for (int child : tree.get(num)) {
            if (visit[child]) continue;
            trace(child, check);
        }
    }

    static void dfs(int num) {
        DP1[num] = cost[num];
        DP2[num] = 0;

        for (int child : tree.get(num)) {
            if (DP1[child] != 0) continue;
            dfs(child);
            DP1[num] += DP2[child];
            DP2[num] += Math.max(DP1[child], DP2[child]);
        }
    }
}

