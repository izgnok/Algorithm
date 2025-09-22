import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] map;
    static Node start;

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        start = new Node();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    start.setRed(i, j);
                    map[i][j] = '.';
                }
                if (map[i][j] == 'B') {
                    start.setBlue(i, j);
                    map[i][j] = '.';
                }
            }
        }

        sb.append(bfs());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);

        boolean[][][][] visit = new boolean[N][M][N][M];
        visit[start.redX][start.redY][start.blueX][start.blueY] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int[] dir : dir) {
                int redX = node.redX;
                int redY = node.redY;
                int blueX = node.blueX;
                int blueY = node.blueY;

                boolean redEnd = false;
                boolean blueEnd = false;
                int redCnt = 0;
                int blueCnt = 0;

                while (!redEnd || !blueEnd) {
                    if (!redEnd) {
                        redX = redX + dir[0];
                        redY = redY + dir[1];
                        redCnt++;
                    }
                    if (redX < 0 || redX >= N || redY < 0 || redY >= M || map[redX][redY] == '#') {
                        redX -= dir[0];
                        redY -= dir[1];
                        redEnd = true;
                    } else if (map[redX][redY] == 'O') redEnd = true;


                    if (!blueEnd) {
                        blueX = blueX + dir[0];
                        blueY = blueY + dir[1];
                        blueCnt++;
                    }
                    if (blueX < 0 || blueX >= N || blueY < 0 || blueY >= M || map[blueX][blueY] == '#') {
                        blueX -= dir[0];
                        blueY -= dir[1];
                        blueEnd = true;
                    } else if (map[blueX][blueY] == 'O') break;
                }

                if (map[blueX][blueY] == 'O') continue;
                if (redX == blueX && redY == blueY) {
                    if (redCnt > blueCnt) {
                        redX -= dir[0];
                        redY -= dir[1];
                    } else {
                        blueX -= dir[0];
                        blueY -= dir[1];
                    }
                }
                if (visit[redX][redY][blueX][blueY]) continue;
                if (map[redX][redY] == 'O') return node.count + 1;
                visit[redX][redY][blueX][blueY] = true;
                q.add(new Node(redX, redY, blueX, blueY, node.count + 1));
            }
        }
        return -1;
    }

    static public class Node {
        int redX, redY;
        int blueX, blueY;
        int count = 0;

        Node() {
        }

        public void setRed(int redX, int redY) {
            this.redX = redX;
            this.redY = redY;
        }

        public void setBlue(int blueX, int blueY) {
            this.blueX = blueX;
            this.blueY = blueY;
        }

        Node(int redX, int redY, int blueX, int blueY, int count) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.count = count;
        }
    }

}