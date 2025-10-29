import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int N, K;
    static PriorityQueue<Node> pq;
    static int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.num, o2.num));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) pq.add(new Node(i, j, map[i][j]));
            }
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;

        while (S-- > 0) {
            if (pq.isEmpty()) break;
            simulation();
        }

        sb.append(map[X][Y]).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void simulation() {

        List<Node> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int[] dir : dirs) {
                int row = node.x + dir[0];
                int col = node.y + dir[1];
                if (row < 0 || row >= N || col < 0 || col >= N) continue;
                if (map[row][col] != 0) continue;
                map[row][col] = node.num;
                list.add(new Node(row, col, node.num));
            }
        }
        for (Node node : list) pq.add(node);
    }

    static class Node {

        int x, y, num;

        Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public String toString() {
            return "X: " + x + " Y: " + y + " Num: " + num;
        }
    }
}