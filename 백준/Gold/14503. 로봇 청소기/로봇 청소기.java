import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;

    static Node robot;
    static boolean die;
    static int count;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        robot = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = 0;
        while (!die) {
            simulation();
        }
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void simulation() {

        // 1. 현재 칸 청소X
        if (map[robot.x][robot.y] == 0) {
            map[robot.x][robot.y] = -1;
            count++;
            return;
        }

        boolean check = false;
        for (int i = 0; i < 4; i++) {
            int row = robot.x + dir[i][0];
            int col = robot.y + dir[i][1];
            if (row < 0 || row >= N || col < 0 || col >= M) continue;
            if (map[row][col] == 0) {
                check = true;
                break;
            }
        }

        // 2. 청소되지 않은 빈칸이 없는 경우
        if (!check) {
            move1();
        }
        // 3. 청소되지 않은 빈칸이 있는 경우
        else {
            rotate();
            move2();
        }
    }

    static void move1() {
        if (robot.dir == 0) {
            robot.x++;
        } else if (robot.dir == 1) {
            robot.y--;

        } else if (robot.dir == 2) {
            robot.x--;

        } else {
            robot.y++;
        }
        if (robot.x < 0 || robot.x >= N || robot.y < 0 || robot.y >= M) die = true;
        if (map[robot.x][robot.y] == 1) die = true;
    }

    static void move2() {

        int row = robot.x;
        int col = robot.y;

        if (robot.dir == 0) {
            row--;
        } else if (robot.dir == 1) {
            col++;
        } else if (robot.dir == 2) {
            row++;
        } else {
            col--;
        }
        if (row < 0 || row >= N || col < 0 || col >= M) return;
        if (map[row][col] != 0) return;

        robot.x = row;
        robot.y = col;
    }

    static void rotate() {
        robot.dir--;
        if (robot.dir < 0) robot.dir = 3;
    }

    static class Node {
        int x, y, dir;

        Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
