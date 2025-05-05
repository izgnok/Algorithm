import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int result = bfs();
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(1, 0, 0));

        HashSet<Node> set = new HashSet<>();
        set.add(new Node(0, 0));
        set.add(new Node(1, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.display == N) {
                return node.time;
            }
            int display = node.display;
            int clip = node.clip;
            int time = node.time;

            // 이모티콘 복사
            if (!set.contains(new Node(display, display))) {
                q.add(new Node(display, display, time + 1));
                set.add(new Node(display, display));
            }

            // 이모티콘 출력
            if (!set.contains(new Node(display + clip, clip))) {
                q.add(new Node(display + clip, clip, time + 1));
                set.add(new Node(display + clip, clip));
            }

            // 이모티콘 삭제
            if (!set.contains(new Node(display - 1, clip))) {
                q.add(new Node(display - 1, clip, time + 1));
                set.add(new Node(display - 1, clip));
            }
        }
        return -1;
    }

    static class Node {
        int display, clip, time;

        Node(int display, int clip) {
            this.display = display;
            this.clip = clip;
        }

        Node(int display, int clip, int time) {
            this.display = display;
            this.clip = clip;
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return this.display == node.display && this.clip == node.clip;
        }

        @Override
        public int hashCode() {
            return Objects.hash(display, clip);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "display=" + display +
                    ", clip=" + clip +
                    ", time=" + time +
                    '}';
        }
    }
}
