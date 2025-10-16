import java.io.*;
import java.util.*;

public class Main {

    static int start, end;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            nodes = new Node[10001];
            bfs(start, end);

            Deque<Character> stack = new ArrayDeque<>();
            int cur = end;
            while (true) {
                Node node = nodes[cur];
                if(node.pre == -1) break;
                stack.addLast(node.cmd);
                cur = node.pre;
            }
            while (!stack.isEmpty()) sb.append(stack.pollLast());
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void bfs(int start, int end) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        HashSet<Integer> set = new HashSet<>();
        set.add(start);
        nodes[start] = new Node(-1, ' ');

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == end) break;


            // D
            int next = (cur * 2) % 10000;
            if (!set.contains(next)) {
                set.add(next);
                q.add(next);
                nodes[next] = new Node(cur, 'D');
            }

            // S
            next = (cur + 9999) % 10000;
            if (!set.contains(next)) {
                set.add(next);
                q.add(next);
                nodes[next] = new Node(cur, 'S');
            }

            // L
            next = (cur % 1000) * 10 + cur / 1000;
            if (!set.contains(next)) {
                set.add(next);
                q.add(next);
                nodes[next] = new Node(cur, 'L');
            }

            // R
            next = (cur / 10) + (cur % 10) * 1000;
            if (!set.contains(next)) {
                set.add(next);
                q.add(next);
                nodes[next] = new Node(cur, 'R');
            }
        }
    }

    public static class Node {
        int pre;
        char cmd;

        public Node(int pre, char cmd) {
            this.pre = pre;
            this.cmd = cmd;
        }
    }
}