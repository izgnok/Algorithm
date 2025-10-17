import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static char[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int result = 0;
        while (true) {
            int cnt = 0;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] == '.') continue;
                    if (bomb(new Node(i, j))) cnt++;
                }
            }
            if (cnt == 0) break;
            result++;
            down();
        }

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void down() {
        for (int j = 0; j < 6; j++) {
            for (int i = 10; i >= 0; i--) {
                if (map[i][j] == '.') continue;
                int cur = i;
                while (cur < 11 && map[cur + 1][j] == '.') {
                    cur++;
                }
                if (cur == i) continue;
                map[cur][j] = map[i][j];
                map[i][j] = '.';
            }
        }
    }

    static boolean bomb(Node start) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);
        boolean[][] visit = new boolean[12][6];
        visit[start.x][start.y] = true;
        List<Node> list = new ArrayList<>();

        while (!q.isEmpty()) {
            Node cur = q.poll();
            list.add(cur);

            for (int[] dir : dir) {
                int x = cur.x + dir[0];
                int y = cur.y + dir[1];

                if (x < 0 || x >= 12 || y < 0 || y >= 6) continue;
                if (map[x][y] != map[start.x][start.y]) continue;
                if (visit[x][y]) continue;
                q.add(new Node(x, y));
                visit[x][y] = true;
            }
        }

        if (list.size() < 4) return false;
        for (Node node : list) {
            map[node.x][node.y] = '.';
        }
        return true;
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

