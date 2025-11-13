import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parent;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Pos[] list = new Pos[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[i] = new Pos(a, b);
        }

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;


        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.cost, o2.cost));
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                Pos A = list[i];
                Pos B = list[j];
                double cost = Math.sqrt(Math.pow(A.x - B.x, 2) + Math.pow(A.y - B.y, 2));
                pq.add(new Node(i, j, cost));
            }
        }

        count = 0;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        double result = 0;
        while (!pq.isEmpty()) {
            if (count == N - 1) break;
            Node node = pq.poll();
            if (!union(node.a, node.b)) continue;
            result += node.cost;
        }

        sb.append(String.format("%.2f", result));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;
        parent[b] = a;
        count++;
        return true;
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node {
        int a, b;
        double cost;

        Node(int a, int b, double cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }
}