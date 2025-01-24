import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[][] map;

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) -'0';
            }
        }

        int answer = bfs();
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.count));
        boolean[][] visit = new boolean[N][N];

        pq.add(new Node(0, 0, 0));
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visit[node.x][node.y]) continue;
            visit[node.x][node.y] = true;

            for(int i=0; i<4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];

                if(row < 0 || row >= N || col < 0 || col >= N) continue;
                if(visit[row][col]) continue;
                if(row == N - 1 && col == N -1) {
                    return node.count;
                }

                int next_count = node.count;
                if(map[row][col] == 0) next_count++;
                pq.add(new Node(row, col, next_count));
            }
        }
        return -1;
    }


    static class Node {
        int x, y, count;

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", count=" + count +
                    '}';
        }

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

}