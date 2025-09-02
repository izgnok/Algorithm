import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int result = dijkstra();
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.count - o2.count);
        pq.add(new Node(0, 0, 0));

        int[][] visit = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(visit[i], 987654321);
        visit[0][0] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visit[node.x][node.y] < node.count) continue;
            if (node.x == N - 1 && node.y == M - 1) return node.count;

            for (int i = 0; i < 4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                int count = node.count;

                if (row < 0 || row >= N || col < 0 || col >= M) continue;
                if (map[row][col] == 1) count++;
                if (visit[row][col] <= count) continue;
                visit[row][col] = count;
                pq.add(new Node(row, col, count));
            }
        }
        return -1;
    }

    static class Node {
        int x, y, count;

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

}