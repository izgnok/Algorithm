import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] map;
    static Queue<Loc> next = new ArrayDeque<>();
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    next.add(new Loc(i, j));
                }

                if(i == 0 || i == N - 1 || j == 0 || j == M - 1) {
                    map[i][j] = -1;
                }
            }
        }

        int time =0;
        while(!next.isEmpty()) {
            time++;
            check();
            bfs();
        }
        sb.append(time);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs() {
        int[][] copy = new int[N][M];
        for(int i=0; i<N; i++) {
            copy[i] = map[i].clone();
        }

        int size = next.size();
        for(int k=0; k<size; k++) {
            Loc cur = next.poll();

            int count = 0;
            for(int i=0; i<4; i++) {
                int row = cur.x + dir[i][0];
                int col = cur.y + dir[i][1];

                if(row < 0 || row >= N || col < 0 || col >= M || map[row][col] != -1) {
                    continue;
                }
                if(++count == 2) break;
            }

            if(count == 2) {
                copy[cur.x][cur.y] = 0;
            }
            else {
                next.add(cur);
            }
        }

        for(int i=0; i<N; i++) {
            map[i] = copy[i].clone();
        }
    }

    static void check() {
        Queue<Loc> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][M];
        q.add(new Loc(0,0));
        visit[0][0] = true;

        while(!q.isEmpty()) {
            Loc cur = q.poll();
            for(int i=0; i<4; i++) {
                int row = cur.x + dir[i][0];
                int col = cur.y + dir[i][1];
                if(row < 0 || row >= N || col < 0  || col >= M || map[row][col] == 1 || visit[row][col]) {
                    continue;
                }
                map[row][col] = -1;
                visit[row][col] = true;
                q.add(new Loc(row,col));
            }
        }
    }

    static public class Loc {
        int x, y;

        Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Loc{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}