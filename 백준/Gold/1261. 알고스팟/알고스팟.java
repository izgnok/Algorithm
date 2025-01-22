import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o.count)));
        boolean[][] visit = new boolean[N][M];

        pq.add(new Node(0,0,0));
        visit[0][0] = true;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            for(int i=0; i<4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];

                if(row < 0 || row >= N || col < 0 || col >= M) continue;
                if(visit[row][col]) continue;

                if(row == N - 1 && col == M - 1) {
                    answer = node.count;
                    return;
                }
                visit[row][col] = true;
                int next_count = node.count;
                if(map[row][col] == 1) next_count++;
                pq.add(new Node(row, col, next_count));
            }

        }
    }

    static class Node {
        int x, y, count;

        Node(int x, int y, int count)  {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

}