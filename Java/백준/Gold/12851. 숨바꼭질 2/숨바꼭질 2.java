import org.w3c.dom.Node;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int start, end;
    static int min = Integer.MAX_VALUE;
    static int result = 0;
    static int[] dir = {-1,1,2};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        if(start == end) {
            min = 0;
            result = 1;
        }
        else bfs(start);

        sb.append(min).append("\n");
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void bfs(int start) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visit = new boolean[100001];
        q.offer(new Node(start, 0));
        visit[start] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();
            if(node.time >= min) return;
            visit[node.x] = true;

            for(int i=0; i<3; i++) {
                int next;
                if(i < 2) {
                    next = node.x + dir[i];
                }
                else {
                    next = node.x * dir[i];
                }

                if(next < 0 || next > 100000) {
                    continue;
                }
                if(visit[next]) {
                    continue;
                }

                if(next == end) {
                    if(min > node.time + 1) {
                        min = node.time + 1;
                        result = 1;
                    }
                    else if(min == node.time + 1) {
                        result++;
                    }
                    continue;
                }

                q.offer(new Node(next, node.time + 1));
            }
        }
    }

    // 현재 위치 x와 현재 위치의 도달한 시간을 저장하는 클래스
    static class Node {
        int x;
        int time;

        Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}