import java.io.*;
import java.util.*;

public class Main {

    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new HashMap<>(N + M);
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map.put(x, y);
        }

        int result = bfs();
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(1, 0));

        int[] visit = new int[101];
        Arrays.fill(visit, 987654321);
        
        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 1; i <= 6; i++) {
                int next = node.x + i;
                if (next > 100) break;
                next = map.getOrDefault(next, next);
                if (next == 100) return node.count + 1;
                if (visit[next] <= node.count + 1) continue;
                visit[next] = node.count + 1;
                q.add(new Node(next, node.count + 1));
            }
        }
        return -1;
    }

    static class Node {
        int x, count;

        Node(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }
}