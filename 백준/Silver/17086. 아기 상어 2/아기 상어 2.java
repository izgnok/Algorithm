
import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static List<Node> sharks;

    static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        sharks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) continue;
                pq.add(bfs(new Node(i, j)));
            }
        }
        sb.append(pq.poll());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs(Node start) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][M];
        q.add(start);
        visit[start.x][start.y] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 8; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                if (row < 0 || row >= N || col < 0 || col >= M) continue;
                if (visit[row][col]) continue;
                if (map[row][col] == 1) {
                    return node.count + 1;
                }
                visit[row][col] = true;
                q.add(new Node(row, col, node.count + 1));
            }
        }
        return -1;
    }


    static class Node {
        int x, y, count;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
