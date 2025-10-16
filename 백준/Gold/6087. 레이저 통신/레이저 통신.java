import java.io.*;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] map;
    static Node start, end;
    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'C') {
                    if (start == null) start = new Node(i, j, 0, 0);
                    else end = new Node(i, j, 0, 0);
                }
            }
        }

        int result = bfs();
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.count, o2.count));
        boolean[][][] visit = new boolean[N][M][4];
        for (int i = 0; i < 4; i++) {
            pq.add(new Node(start.x, start.y, i, 0));
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (end.equals(node)) return node.count;
            if(visit[node.x][node.y][node.dir]) continue;
            visit[node.x][node.y][node.dir] = true;

            // 직진
            int row = node.x + dir[node.dir][0];
            int col = node.y + dir[node.dir][1];
            if (row >= 0 && row < N && col >= 0 && col < M) {
                if (map[row][col] != '*' && !visit[row][col][node.dir]) {
                    pq.add(new Node(row, col, node.dir, node.count));
                }
            }

            // 방향전환
            int[] changeDir = change(node.dir);
            for (int cd : changeDir) {
                row = node.x + dir[cd][0];
                col = node.y + dir[cd][1];
                if (row < 0 || row >= N || col < 0 || col >= M) continue;
                if (map[row][col] == '*') continue;
                if (visit[row][col][cd]) continue;
                pq.add(new Node(row, col, cd, node.count + 1));
            }
        }
        return -1;
    }

    static int[] change(int dir) {
        if (dir == 0 || dir == 1)
            return new int[]{2, 3};
        else
            return new int[]{0, 1};
    }

    static class Node {
        int x, y, dir, count;

        public Node(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            Node obj = (Node) o;
            return this.x == obj.x && this.y == obj.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }

        @Override
        public String toString() {
            return "(" + this.x + "," + this.y + ")" + ", dir:  " + this.dir + ", count: " + this.count;
        }
    }

}