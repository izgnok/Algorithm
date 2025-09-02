import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int start, end;
    static int[] dir = {-1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        sb.append(bfs());
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
                int nextX = node.x;
                int nextTime = node.time;
                if (i < 2) {
                    nextX += dir[i];
                    nextTime++;
                } else nextX *= 2;
                
                if (nextX < 0 || nextX >= 100001) continue;
                if (visit[nextX] <= nextTime) continue;
                visit[nextX] = nextTime;
                pq.add(new Node(nextX, nextTime));
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