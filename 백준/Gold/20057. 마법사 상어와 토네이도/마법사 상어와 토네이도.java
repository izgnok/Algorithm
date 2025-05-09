import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static int result = 0;
    static HashMap<Integer, int[][]> spreadMap;
    static int[][] dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        hashMapInit();
        simulation(N / 2, N / 2);
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void simulation(int row, int col) {
        int dist = 1;
        int direct = 0;

        while (true) {
            for (int k = 0; k < 2; k++) {
                for (int i = 0; i < dist; i++) {
                    row += dir[direct][0];
                    col += dir[direct][1];
                    spreadSand(row, col, direct);
                    if (row == 0 && col == 0) return;
                }
                direct = (direct + 1) % 4;
            }
            dist++;
        }
    }


    static void spreadSand(int row, int col, int direct) {
        int[][] spread = spreadMap.get(direct);
        int sand = map[row][col];
        int totalSpread = 0;

        for (int[] s : spread) {
            int nextRow = row + s[0];
            int nextCol = col + s[1];
            int sum = (sand * s[2]) / 100;
            totalSpread += sum;
            if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
                map[nextRow][nextCol] += sum;
            } else {
                result += sum;
            }
        }

        int alphaRow = row + dir[direct][0];
        int alphaCol = col + dir[direct][1];
        int remain = sand - totalSpread;
        if (alphaRow >= 0 && alphaRow < N && alphaCol >= 0 && alphaCol < N) {
            map[alphaRow][alphaCol] += remain;
        } else {
            result += remain;
        }
        map[row][col] = 0;
    }

    static void hashMapInit() {
        spreadMap = new HashMap<>();
        spreadMap.put(0, new int[][]{{-1, 1, 1}, {1, 1, 1}, {1, 0, 7}, {-1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {1, -1, 10}, {-1, -1, 10}, {0, -2, 5}});
        spreadMap.put(1, new int[][]{{-1, -1, 1}, {-1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {0, -2, 2}, {0, 2, 2}, {1, -1, 10}, {1, 1, 10}, {2, 0, 5}});
        spreadMap.put(2, new int[][]{{-1, -1, 1}, {1, -1, 1}, {1, 0, 7}, {-1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {1, 1, 10}, {-1, 1, 10}, {0, 2, 5}});
        spreadMap.put(3, new int[][]{{1, -1, 1}, {1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {0, -2, 2}, {0, 2, 2}, {-1, -1, 10}, {-1, 1, 10}, {-2, 0, 5}});
    }
}
