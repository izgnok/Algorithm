import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int size;
    static Castle[][] castle;
    static int[][] map;
    static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static List<Integer> room;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        castle = new Castle[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                int left = num & 1;
                int top = (num >> 1) & 1;
                int right = (num >> 2) & 1;
                int bottom = (num >> 3) & 1;
                castle[i][j] = new Castle(left, right, top, bottom);
            }
        }

        map = new int[N][M];
        room = new ArrayList<>();
        size = 0;
        int maxOneRoom = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) continue;
                size++;
                map[i][j] = size;
                room.add(bfs(new Node(i, j)));
                maxOneRoom = Math.max(maxOneRoom, room.get(size - 1));
            }
        }

        int maxTwoRoom = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = map[i][j];
                int sum = room.get(num - 1);
                Castle cur = castle[i][j];
                for (int k = 0; k < 4; k++) {
                    if (cur.block[k] == 0) continue;

                    int x = i + dir[k][0];
                    int y = j + dir[k][1];
                    if (x < 0 || x >= N || y < 0 || y >= M) continue;
                    if (map[x][y] == num) continue;
                    int tmp = sum + room.get(map[x][y] - 1);
                    maxTwoRoom = Math.max(maxTwoRoom, tmp);
                }
            }
        }

        sb.append(size).append("\n");
        sb.append(maxOneRoom).append("\n");
        sb.append(maxTwoRoom);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs(Node start) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);

        int cnt = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            Castle cur = castle[node.x][node.y];
            cnt++;

            for (int i = 0; i < 4; i++) {
                if (cur.block[i] == 1) continue;
                int x = node.x + dir[i][0];
                int y = node.y + dir[i][1];

                if (x < 0 || x >= N || y < 0 || y >= M) continue;
                if (map[x][y] != 0) continue;
                map[x][y] = size;
                q.add(new Node(x, y));
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
            return "(" + x + "," + y + ")";
        }
    }

    static class Castle {
        int[] block = new int[4];

        Castle(int left, int right, int top, int bottom) {
            this.block[0] = left;
            this.block[1] = right;
            this.block[2] = top;
            this.block[3] = bottom;
        }

        @Override
        public String toString() {
            return "left: " + block[0] + ", right: " + block[1] + ", top: " + block[2] + ", bottom: " + block[3];
        }
    }
}