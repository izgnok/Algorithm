import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int max;
    static int result;

    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(map[i][j], max);
            }
        }

        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = true;
                dfs(1, map[i][j], i, j);
                visit[i][j] = false;
                check(i, j);
            }
        }

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void check(int x, int y) {
        int[] arr = new int[4];
        int count = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int row = x + dir[i][0];
            int col = y + dir[i][1];
            if (row < 0 || row >= N || col < 0 || col >= M) count++;
            else arr[i] = map[row][col];
            min = Math.min(arr[i], min);
            sum += arr[i];
        }
        if (count > 1) return;
        result = Math.max(result, map[x][y] + (sum - min));
    }

    static void dfs(int depth, int sum, int x, int y) {
        if (depth == 4) {
            result = Math.max(result, sum);
            return;
        }
        if (result >= sum + (4 - depth) * max) return;

        for (int i = 0; i < 4; i++) {
            int row = x + dir[i][0];
            int col = y + dir[i][1];

            if (row < 0 || row >= N || col < 0 || col >= M) continue;
            if (visit[row][col]) continue;

            visit[row][col] = true;
            dfs(depth + 1, sum + map[row][col], row, col);
            visit[row][col] = false;
        }
    }
}