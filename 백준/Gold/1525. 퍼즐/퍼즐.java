import java.io.*;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static Queue<Node> q;
    static HashSet<Integer> set;
    static int[] dir = {-3, -1, 1, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int start = 0;
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                start += Integer.parseInt(st.nextToken()) * (int) (Math.pow(10, 3 * i + j));
            }
        }
        q = new ArrayDeque<>();
        q.add(new Node(start, 0));
        set = new HashSet<>();
        set.add(start);

        sb.append(bfs());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs() {
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.num == 87654321) return node.count;

            int zeroX = -1;
            for (int i = 0; i < 9; i++) {
                if (node.num % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i) != 0) continue;
                zeroX = i;
                break;
            }

            for (int i = 0; i < 4; i++) {
                if (zeroX % 3 == 0 && i == 1) continue;
                if (zeroX % 3 == 2 && i == 2) continue;
                
                int x = zeroX + dir[i];
                if (x < 0 || x >= 9) continue;

                int num = node.num % (int) Math.pow(10, x + 1) / (int) Math.pow(10, x);
                int next = node.num - num * (int) Math.pow(10, x) + num * (int) Math.pow(10, zeroX);

                if (set.contains(next)) continue;
                set.add(next);
                q.add(new Node(next, node.count + 1));
            }
        }
        return -1;
    }

    static class Node {
        int num, count;

        Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

}