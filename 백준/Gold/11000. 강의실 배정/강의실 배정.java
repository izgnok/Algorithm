import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Node[] list = new Node[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list[i] = new Node(start, end);
        }
        Arrays.sort(list, ((o1, o2) -> {
            if(o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        }));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(list[0].end);
        for(int i=1; i<N; i++) {
            if(pq.peek() <= list[i].start) {
                pq.poll();
            }
            pq.add(list[i].end);
        }
        sb.append(pq.size());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static public class Node {
        int start, end;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
