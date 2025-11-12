import java.io.*;
import java.util.*;

public class Main {

    static int N, M, T;
    static int[][] map;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = bfs();
        sb.append(result == -1 ? "Fail" : result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 0, 0));

        boolean[][][] visit = new boolean[N][M][2];
        visit[0][0][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.x == N - 1 && node.y == M - 1) return node.count;
            if (node.count >= T) continue;

            for (int[] dir : dirs) {
                int row = node.x + dir[0];
                int col = node.y + dir[1];

                if (row < 0 || row >= N || col < 0 || col >= M) continue;
                if (node.sword == 0 && map[row][col] == 1) continue;

                int nextSword = node.sword;
                if (map[row][col] == 2) nextSword = 1;
                if (visit[row][col][nextSword]) continue;

                q.add(new Node(row, col, node.count + 1, nextSword));
                visit[row][col][nextSword] = true;
            }
        }
        return -1;
    }


    static class Node {
        int x, y, count, sword;

        public Node(int x, int y, int count, int sword) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.sword = sword;
        }
    }
}