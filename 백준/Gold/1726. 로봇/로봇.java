import java.io.*;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static Node start, end;

    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        start = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
        st = new StringTokenizer(br.readLine());
        end = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

        int result = bfs();
        sb.append(result).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);

        boolean[][][] visit = new boolean[N][M][4];
        visit[start.x][start.y][start.dir] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.equals(end)) return node.count;

            // 이동
            for (int k = 1; k <= 3; k++) {
                int row = node.x + (dir[node.dir][0] * k);
                int col = node.y + (dir[node.dir][1] * k);
                if (row >= 0 && row < N && col >= 0 && col < M) {
                    if (map[row][col] == 0) {
                        if (!visit[row][col][node.dir]) {
                            visit[row][col][node.dir] = true;
                            q.add(new Node(row, col, node.dir, node.count + 1));
                        }
                    } else break;
                } else break;
            }

            // 방향전환
            int chagnedir = changeDir(node.dir, 1);
            if (map[node.x][node.y] == 0 && !visit[node.x][node.y][chagnedir]) {
                visit[node.x][node.y][chagnedir] = true;
                q.add(new Node(node.x, node.y, chagnedir, node.count + 1));
            }
            chagnedir = changeDir(node.dir, 0);
            if (map[node.x][node.y] == 0 && !visit[node.x][node.y][chagnedir]) {
                visit[node.x][node.y][chagnedir] = true;
                q.add(new Node(node.x, node.y, chagnedir, node.count + 1));
            }
        }
        return -1;
    }

    static int changeDir(int dir, int left) {

        if (left == 1) {
            if (dir == 0) {
                dir = 3;
            } else if (dir == 1) {
                dir = 2;
            } else if (dir == 2) {
                dir = 0;
            } else if (dir == 3) {
                dir = 1;
            }
        } else {
            if (dir == 0) {
                dir = 2;
            } else if (dir == 1) {
                dir = 3;
            } else if (dir == 2) {
                dir = 1;
            } else if (dir == 3) {
                dir = 0;
            }
        }
        return dir;
    }

    static class Node {
        int x, y, dir, count;

        Node(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            //if (!(o instanceof Node)) return false;
            Node obj = (Node) o;
            return x == obj.x && y == obj.y && dir == obj.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, dir);
        }

        @Override
        public String toString() {
            return "X: " + x + " Y: " + y + " Dir: " + dir + " Count: " + count;
        }
    }
}