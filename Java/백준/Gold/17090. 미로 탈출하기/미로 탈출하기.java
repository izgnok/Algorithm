import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] DP;
    static int result = 0;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = str.charAt(j);
                if(c == 'U') {
                    map[i][j] = 0;
                }
                else if(c == 'D') {
                    map[i][j] = 1;
                }
                else if(c == 'L') {
                    map[i][j] = 2;
                }
                else {
                    map[i][j] = 3;
                }
            }
        }

        DP = new int[N][M];
        visit = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(dfs(i,j) == 1) result++;
            }
        }

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int dfs(int row, int col) {
        if(row < 0 || row >= N || col < 0 || col >= M) return 1; // 경계선 밖으로 도달
        
        if(DP[row][col] != 0 ) { // 이미 결과가 정해져있는 경우
            return DP[row][col];
        }
        
        if(visit[row][col]) return -1; // 순회하는 경우

        visit[row][col] = true;
        if(map[row][col] == 0) { // 상
            DP[row][col] = dfs(row-1, col);
        }
        
        else if(map[row][col] == 1) { // 하
            DP[row][col] = dfs(row+1, col);
        }
        
        else if(map[row][col] == 2) { // 좌
            DP[row][col] = dfs(row, col-1);
        }
        
        else { // 우
            DP[row][col] = dfs(row, col+1);
        }
        return DP[row][col];
    }
}