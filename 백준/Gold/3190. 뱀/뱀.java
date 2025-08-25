import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static boolean[][] apple;
    static boolean[][] sneak;
    static Queue<CMD> cmd;
    static boolean die;
    static LinkedList<Node> list;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        sneak = new boolean[N][N];
        apple = new boolean[N][N];
        sneak[0][0] = true;

        int m = Integer.parseInt(br.readLine().trim());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            apple[x - 1][y - 1] = true;
        }

        cmd = new ArrayDeque<>();
        int k = Integer.parseInt(br.readLine().trim());
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            cmd.add(new CMD(time, dir));
        }

        list = new LinkedList<>();
        list.add(new Node(0, 0, 3));

        int time = 0;
        die = false;
        while (!die) {
            if (!cmd.isEmpty() && cmd.peek().time == time) {
                changeDir(cmd.peek().dir);
                cmd.poll();
            }
            simulation();
            time++;
        }
        sb.append(time);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void changeDir(String dir) {
        Node node = list.getLast();
        if (dir.equals("L")) {
            if (node.dir == 0) node.dir = 2;
            else if (node.dir == 1) node.dir = 3;
            else if (node.dir == 2) node.dir = 1;
            else node.dir = 0;
        } else {
            if (node.dir == 0) node.dir = 3;
            else if (node.dir == 1) node.dir = 2;
            else if (node.dir == 2) node.dir = 0;
            else node.dir = 1;
        }
    }

    static void simulation() {
        Node node = list.getLast();
        int row = node.x + dir[node.dir][0];
        int col = node.y + dir[node.dir][1];

        if (row < 0 || row >= N || col < 0 || col >= N) {
            die = true;
            return;
        }

        if (sneak[row][col]) {
            die = true;
            return;
        }

        if (apple[row][col]) {
            apple[row][col] = false;
        }
        else {
            Node tmp = list.getFirst();
            sneak[tmp.x][tmp.y] = false;
            list.removeFirst();
        }

        list.add(new Node(row, col, node.dir));
        sneak[row][col] = true;
    }

    static class Node {
        int x, y, dir;

        Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static class CMD {
        int time;
        String dir;

        CMD(int time, String dir) {
            this.time = time;
            this.dir = dir;
        }
    }
}