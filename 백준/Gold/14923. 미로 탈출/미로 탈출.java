import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;

    static Node start;
    static Node end;

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        start = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        st = new StringTokenizer(br.readLine());
        end = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = bfs();
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);

        boolean[][][] visit = new boolean[N][M][2];
        visit[start.x][start.y][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.equals(end)) {
                return node.count;
            }
            for (int i = 0; i < 4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];

                if (row < 0 || row >= N || col < 0 || col >= M) continue;
                if (map[row][col] == 1) {
                    if (node.broken == 1) continue;
                    if (visit[row][col][1]) continue;
                    visit[row][col][1] = true;
                    q.add(new Node(row, col, node.count + 1, 1));
                } else {
                    if (visit[row][col][node.broken]) continue;
                    visit[row][col][node.broken] = true;
                    q.add(new Node(row, col, node.count + 1, node.broken));
                }
            }
        }
        return -1;
    }

    static class Node {
        int x, y, count, broken;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int x, int y, int count, int broken) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.broken = broken;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || !(o instanceof Node)) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", count=" + count +
                    ", broken=" + broken +
                    '}';
        }
    }
}
