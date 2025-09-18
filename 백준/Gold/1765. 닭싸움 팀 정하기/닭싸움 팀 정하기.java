import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<List<Integer>> enemy;
    static List<Node> list;
    static int[] parent;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        list = new ArrayList<>();
        enemy = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            enemy.add(new ArrayList<>());
            parent[i] = i;
        }

        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd.equals("E")) {
                enemy.get(a).add(b);
                enemy.get(b).add(a);
            } else list.add(new Node(a, b));
        }

        result = N;
        for (int i = 1; i <= N; i++) {
            for (int next : enemy.get(i)) {
                for (int child : enemy.get(next)) {
                    list.add(new Node(i, child));
                }
            }
        }

        for (Node node : list) union(node.a, node.b);

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;
        parent[b] = a;
        result--;
    }

    static class Node {
        int a, b;

        Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}