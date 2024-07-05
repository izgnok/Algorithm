import org.w3c.dom.Node;

import java.io.*;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static Node start;
    static Queue<Node> fire = new ArrayDeque<>();
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            String str = br.readLine().trim();
            for(int j=0; j<M; j++) {
                char c = str.charAt(j);
                if(c == 'J') { // 출발점
                    start = new Node(i,j, 0);
                    map[i][j] = 9999;
                }
                else if (c == '#') { // 벽
                    map[i][j] = -1;
                }
                else if(c == 'F'){
                    fire.add(new Node(i,j, 0));
                }
                else {
                    map[i][j] = 9999;
                }
            }
        }
        spread();
        bfs();
        if(result != 0) sb.append(result);
        else sb.append("IMPOSSIBLE");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void spread() { // 불 번지기
        while(!fire.isEmpty()) {
            Node node = fire.poll();

            for(int i=0; i<4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];

                if(row < 0 || row >= N || col < 0 || col >= M) {
                    continue;
                }
                if(map[row][col] == -1) {
                    continue;
                }

                if(map[row][col] > node.count + 1) {
                    map[row][col] = node.count + 1;
                    fire.add(new Node(row, col, node.count + 1));
                }
            }
        }
    }

    static void bfs() { // 탈출
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][M];
        q.add(start);
        visit[start.x][start.y] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();
            if(node.x == 0 || node.x == N-1 || node.y == 0 || node.y == M-1) {
                result = node.count + 1;
                return;
            }

            for(int i=0; i<4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];

                if(row < 0 || row >= N || col < 0 || col >= M) {
                    continue;
                }
                if(map[row][col] == -1) {
                    continue;
                }
                if(visit[row][col]) {
                    continue;
                }
                if(map[row][col] <= node.count + 1) {
                    continue;
                }
                visit[row][col] = true;
                q.add(new Node(row, col, node.count + 1));
            }
        }
    }

    static class Node {
        int x, y, count;

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", count=" + count +
                    '}';
        }
    }
}