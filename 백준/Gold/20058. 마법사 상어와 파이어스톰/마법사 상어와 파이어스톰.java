import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int sum;
    static boolean[][] visit;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        int Q = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (Q-- > 0) {
            int L = Integer.parseInt(st.nextToken());

            int len = (int) Math.pow(2, L);
            int size = N / len;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    rotate(i, j, len);
                }
            }
            delete();
        }

        sum = 0;
        int max = 0;
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visit[i][j]) continue;
                if (map[i][j] == 0) continue;
                max = Math.max(max, bfs(i, j));
            }
        }
        sb.append(sum).append("\n");
        sb.append(max).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void rotate(int x, int y, int len) {
        x *= len;
        y *= len;

        int[][] tmp = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                tmp[j][len - 1 - i] = map[x + i][y + j];
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) map[x + i][y + j] = tmp[i][j];
        }
    }

    static void delete() {

        Queue<Node> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                for (int[] dir : dirs) {
                    int row = i + dir[0];
                    int col = j + dir[1];
                    if (row < 0 || row >= N || col < 0 || col >= N) continue;
                    if (map[row][col] == 0) continue;
                    cnt++;
                }
                if (cnt < 3) q.add(new Node(i, j));
            }
        }

        while (!q.isEmpty()) {
            Node node = q.poll();
            map[node.x][node.y] = Math.max(map[node.x][node.y] - 1, 0);
        }
    }

    static int bfs(int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y));
        visit[x][y] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            sum += map[node.x][node.y];
            cnt++;

            for (int[] dir : dirs) {
                int row = node.x + dir[0];
                int col = node.y + dir[1];
                if (row < 0 || row >= N || col < 0 || col >= N) continue;
                if (map[row][col] == 0) continue;
                if (visit[row][col]) continue;
                visit[row][col] = true;
                q.add(new Node(row, col));
            }
        }
        return cnt;
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}