import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;

    static Queue<Node> cloud;
    static int[][] dir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cloud = new ArrayDeque<>();
        cloud.add(new Node(N - 1, 0));
        cloud.add(new Node(N - 2, 0));
        cloud.add(new Node(N - 1, 1));
        cloud.add(new Node(N - 2, 1));
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            move(d, s);
            rain();
            mage();
            makeCLoud();
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += map[i][j];
            }
        }
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void makeCLoud() {
        boolean[][] check = new boolean[N][N];

        while (!cloud.isEmpty()) {
            Node node = cloud.poll();
            check[node.x][node.y] = true;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (check[i][j]) continue;
                if (map[i][j] < 2) continue;
                map[i][j] -= 2;
                cloud.add(new Node(i, j));
            }
        }
    }

    static void mage() {
        int size = cloud.size();
        for (int i = 0; i < size; i++) {
            Node node = cloud.poll();
            int count = 0;
            for (int k = 1; k < 8; k += 2) {
                int row = node.x + dir[k][0];
                int col = node.y + dir[k][1];
                if (row < 0 || row >= N || col < 0 || col >= N) continue;
                if (map[row][col] == 0) continue;
                count++;
            }
            map[node.x][node.y] += count;
            cloud.add(node);
        }
    }

    static void rain() {
        int size = cloud.size();
        for (int i = 0; i < size; i++) {
            Node node = cloud.poll();
            map[node.x][node.y]++;
            cloud.add(node);
        }
    }

    static void move(int d, int s) {
        int size = cloud.size();
        for (int i = 0; i < size; i++) {
            Node node = cloud.poll();

            node.x += dir[d - 1][0] * s;
            node.y += dir[d - 1][1] * s;

            while (node.x < 0 || node.x >= N) {
                if (node.x < 0) node.x += N;
                else node.x -= N;
            }
            while (node.y < 0 || node.y >= N) {
                if (node.y < 0) node.y += N;
                else node.y -= N;
            }

            cloud.add(node);
        }
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
