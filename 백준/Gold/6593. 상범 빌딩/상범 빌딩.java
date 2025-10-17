import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int N, M, K;
    static int[][] dir = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
    static char[][][] map;
    static Node start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (K == 0 || N == 0 || M == 0) break;
            map = new char[K][N][M];

            for (int k = 0; k < K; k++) {
                for (int i = 0; i < N; i++) {
                    String str = br.readLine();
                    for (int j = 0; j < M; j++) {
                        map[k][i][j] = str.charAt(j);
                        if (map[k][i][j] == 'S') start = new Node(k, i, j, 0);
                        if (map[k][i][j] == 'E') end = new Node(k, i, j, 0);
                    }
                }
                br.readLine();
            }

            int result = bfs();
            sb.append(result == -1 ? "Trapped!" : "Escaped in " + result + " minute(s).").append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);

        boolean[][][] visit = new boolean[K][N][M];
        visit[start.k][start.x][start.y] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int[] dir : dir) {
                int k = cur.k + dir[0];
                int x = cur.x + dir[1];
                int y = cur.y + dir[2];

                if (k < 0 || k >= K || x < 0 || x >= N || y < 0 || y >= M) continue;
                if (map[k][x][y] == 'E') return cur.count + 1;
                if (map[k][x][y] == '#') continue;
                if (visit[k][x][y]) continue;
                visit[k][x][y] = true;
                q.add(new Node(k, x, y, cur.count + 1));
            }
        }
        return -1;
    }

    static class Node {
        int k, x, y, count;

        public Node(int k, int x, int y, int count) {
            this.k = k;
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public String toString() {
            return "(" + k + ", " + x + ", " + y + ")" + ", Count: " + count;
        }

    }
}

