import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int start, end;
    static int[] dir = {-1, 1};
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        parent = new int[100001];
        parent[start] = -1;


        int time = bfs();

        Deque<Integer> dq = new ArrayDeque<>();
        int cur = end;
        while (cur != -1) {
            dq.addFirst(cur);
            cur = parent[cur];
        }

        sb.append(time).append("\n");
        while (!dq.isEmpty()) {
            sb.append(dq.pollFirst()).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    static int bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        pq.add(new Node(start, 0));
        int[] visit = new int[1000001];
        Arrays.fill(visit, 987654321);
        visit[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.x == end) return node.time;

            for (int i = 0; i < 3; i++) {
                int next = node.x;
                if (i < 2) {
                    next += dir[i];
                } else next *= 2;

                if (next < 0 || next >= 100001) continue;
                if (visit[next] <= node.time + 1) continue;
                parent[next] = node.x;
                visit[next] = node.time + 1;
                pq.add(new Node(next, visit[next]));
            }
        }
        return -1;
    }

    static class Node {
        int x, time;

        Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}