import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int[][] map;
    static Node start;

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        map = new int[5][5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        start = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        if (dfs(0, 0, new Node(start.x, start.y))) sb.append("1");
        else sb.append("0");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean dfs(int depth, int count, Node cur) {
        if (count == 2) return true;
        if (depth >= 3) return false;

        int tmp = map[cur.x][cur.y];
        map[cur.x][cur.y] = -1;
        for (int i = 0; i < 4; i++) {
            int row = cur.x + dir[i][0];
            int col = cur.y + dir[i][1];
            if (row < 0 || row >= 5 || col < 0 || col >= 5) continue;
            if (map[row][col] == -1) continue;
            
            int nextCount = map[row][col] == 1 ? count + 1 : count;
            if (dfs(depth + 1, nextCount, new Node(row, col))) return true;
        }
        map[cur.x][cur.y] = tmp;

        return false;
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}