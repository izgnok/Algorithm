
import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static List<Node> sharks;

    static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static boolean[][] visit;
    static Queue<Node> q = new ArrayDeque<>();
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        sharks = new ArrayList<>();
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    q.add(new Node(i, j));
                    visit[i][j] = true;
                }
            }
        }

        bfs();
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Node node = q.poll();
            result = Math.max(node.count, result);

            for (int i = 0; i < 8; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                if (row < 0 || row >= N || col < 0 || col >= M) continue;
                if (visit[row][col]) continue;
                visit[row][col] = true;
                q.add(new Node(row, col, node.count + 1));
            }
        }
    }


    static class Node {
        int x, y, count;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
