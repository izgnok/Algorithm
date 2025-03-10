import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, H;
    static int[][][] tomato;
    static Queue<Node> nextQ;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 앞, 뒤, 좌, 우
    static int[] upDown = {-1, 1}; // 아래, 위
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomato = new int[N][M][H];
        nextQ = new ArrayDeque<>();
        count = 0;
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                    if (tomato[i][j][k] == 1) nextQ.add(new Node(i, j, k));
                    if(tomato[i][j][k] == 1 || tomato[i][j][k] == -1) count++;
                }
            }
        }

        int day = 0;
        while (true) {
            if (count == N * M * H) break;
            if (nextQ.isEmpty()) {
                day = -1;
                break;
            }
            day++;
            bfs();
        }
        sb.append(day);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static public void bfs() {

        int size = nextQ.size();
        for (int k = 0; k < size; k++) {
            Node node = nextQ.poll();

            for (int i = 0; i < 4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];

                if (row < 0 || row >= N || col < 0 || col >= M) continue;
                if (tomato[row][col][node.z] != 0) continue;

                tomato[row][col][node.z] = 1;
                nextQ.add(new Node(row, col, node.z));
            }

            for (int i = 0; i < 2; i++) {
                int nextZ = node.z + upDown[i];

                if (nextZ < 0 || nextZ >= H) continue;
                if (tomato[node.x][node.y][nextZ] != 0) continue;

                tomato[node.x][node.y][nextZ] = 1;
                nextQ.add(new Node(node.x, node.y, nextZ));
            }
        }
        count += nextQ.size();
    }

    static class Node {
        int x, y, z;

        Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
