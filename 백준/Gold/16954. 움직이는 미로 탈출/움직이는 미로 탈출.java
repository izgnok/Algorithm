import java.io.*;
import java.util.*;

public class Main {

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}, {0, 0}};
    static boolean[][][] block;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        block = new boolean[8][8][9];
        for (int i = 0; i < 8; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < 8; j++) {
                if (str.charAt(j) == '#') {
                    for (int k = 0; k < 8; k++) {
                        if (i + k >= 8) break;
                        block[i + k][j][k] = true;
                    }
                }
            }
        }
        boolean possible = bfs();
        if (possible) sb.append("1");
        else sb.append("0");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(7, 0, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.x == 0 && node.y == 7) return true;
            if (node.time == 8) return true;

            boolean[][] visit = new boolean[8][8];
            for (int i = 0; i < 9; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                if (row < 0 || row >= 8 || col < 0 || col >= 8) continue;
                if (block[row][col][node.time]) continue;
                if (block[row][col][node.time + 1]) continue;
                if (visit[row][col]) continue;
                visit[row][col] = true;
                q.add(new Node(row, col, node.time + 1));
            }
        }
        return false;
    }

    static class Node {
        int x, y, time;

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" + "x=" + x + ", y=" + y + ", time=" + time + '}';
        }
    }
}
