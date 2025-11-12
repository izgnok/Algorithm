import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] map;
    static Queue<Node> q;

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        List<Node> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'o') list.add(new Node(i, j, -1, -1, -1));
            }
        }

        q = new ArrayDeque<>();
        q.add(new Node(list.get(0).x1, list.get(0).y1, list.get(1).x1, list.get(1).y1, 0));
        int result = bfs();

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs() {

        HashSet<String> set = new HashSet<>();
        Node start = q.peek();
        set.add(start.x1 + "," + start.y1 + "," + start.x2 + "," + start.y2);

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.count == 10) break;

            for (int[] dir : dirs) {
                int row1 = node.x1 + dir[0];
                int col1 = node.y1 + dir[1];
                int row2 = node.x2 + dir[0];
                int col2 = node.y2 + dir[1];

                int cnt = 0;
                if (row1 < 0 || row1 >= N || col1 < 0 || col1 >= M) cnt++;
                if (row2 < 0 || row2 >= N || col2 < 0 || col2 >= M) cnt++;
                if (cnt == 2) continue;
                if (cnt == 1) return node.count + 1;


                if (map[row1][col1] == '#') {
                    row1 -= dir[0];
                    col1 -= dir[1];
                }
                if (map[row2][col2] == '#') {
                    row2 -= dir[0];
                    col2 -= dir[1];
                }

                String str = row1 + "," + col1 + "," + row2 + "," + col2;
                if (set.contains(str)) continue;

                q.add(new Node(row1, col1, row2, col2, node.count + 1));
                set.add(str);
            }
        }
        return -1;
    }

    static class Node {
        int x1, y1, x2, y2, count;

        Node(int x1, int y1, int x2, int y2, int count) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.count = count;
        }
    }

}