import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;

    static boolean[][] fireVisit;
    static boolean[][] playerVisit;
    static Queue<Node> fire;
    static Queue<Node> player;
    static boolean possible;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            player = new ArrayDeque<>();
            fire = new ArrayDeque<>();
            fireVisit = new boolean[N][M];
            playerVisit = new boolean[N][M];
            map = new char[N][M];
            for (int i = 0; i < N; i++) {
                String str = br.readLine().trim();
                for (int j = 0; j < M; j++) {
                    char c = str.charAt(j);
                    map[i][j] = str.charAt(j);
                    //  불
                    if (c == '*') {
                        fire.add(new Node(i, j));
                        fireVisit[i][j] = true;
                    }
                    // 상근
                    if (c == '@') {
                        player.add(new Node(i, j));
                        playerVisit[i][j] = true;
                    }
                }
            }
            possible = false;
            int time = 0;
            while (!possible && !player.isEmpty()) {
                time++;
                fireBFS();
                playerBFS();
            }
            if (possible) sb.append(time);
            else sb.append("IMPOSSIBLE");
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void fireBFS() {
        int size = fire.size();
        while (size-- > 0) {
            Node node = fire.poll();
            for (int i = 0; i < 4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                if (row < 0 || row >= N || col < 0 || col >= M) continue;
                if (fireVisit[row][col]) continue;
                if (map[row][col] == '*' || map[row][col] == '#') continue;
                map[row][col] = '*';
                fireVisit[row][col] = true;
                fire.add(new Node(row, col));
            }
        }
    }

    static void playerBFS() {
        int size = player.size();
        while (size-- > 0) {
            Node node = player.poll();
            for (int i = 0; i < 4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                if (row < 0 || row >= N || col < 0 || col >= M) {
                    possible = true;
                    return;
                }
                if (map[row][col] == '*' || map[row][col] == '#') continue;
                if (playerVisit[row][col]) continue;
                playerVisit[row][col] = true;
                player.add(new Node(row, col));
            }
        }
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "X: " + this.x + ", Y: " + this.y;
        }
    }
}
