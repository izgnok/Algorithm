import org.w3c.dom.Node;

import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {

    static int N, L, R;
    static int[][] map;
    static boolean check;
    static boolean[][] visit;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            check = false;
            visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visit[i][j]) continue;
                    visit[i][j] = true;
                    bfs(new Node(i, j, map[i][j]));
                }
            }
            if (check) answer++;
            else break;
        }
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs(Node start) {
        Queue<Node> q = new ArrayDeque<>();
        List<Node> list = new ArrayList<>();
        q.add(start);

        int count = 0;
        int people = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            list.add(node);
            people += map[node.x][node.y];
            count++;

            for (int i = 0; i < 4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];

                if (row < 0 || row >= N || col < 0 || col >= N) continue;
                if (visit[row][col]) continue;
                int peopleCount = Math.abs(node.people - map[row][col]);
                if (peopleCount < L || peopleCount > R) continue;
                visit[row][col] = true;
                q.add(new Node(row, col, map[row][col]));
            }
        }

        if (count == 1) return;
        if(people == people / count) return;
        check = true;
        people /= count;
        for (Node node : list) {
            map[node.x][node.y] = people;
        }
    }

    static class Node {
        int x, y, people;

        Node(int x, int y, int people) {
            this.x = x;
            this.y = y;
            this.people = people;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", people=" + people +
                    '}';
        }
    }
}