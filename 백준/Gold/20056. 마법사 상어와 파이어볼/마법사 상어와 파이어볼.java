
import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static Queue<Node> q;

    static int num;
    static HashSet<Integer> die;
    static HashMap<Node, List<Node>> map;
    static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        q = new ArrayDeque<>();
        num = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direct = Integer.parseInt(st.nextToken());
            q.add(new Node(num++, x, y, weight, speed, direct));
        }

        die = new HashSet<>();
        while (K-- > 0) {
            simulation();
        }

        int result = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (die.contains(node.num)) continue;
            result += node.weight;
        }
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void simulation() {
        map = new HashMap<>();
        int size = q.size();
        for (int k = 0; k < size; k++) {
            Node node = q.poll();
            if (die.contains(node.num)) continue;
            move(node);
            q.add(node);
        }

        for (List<Node> list : map.values()) {
            if (list.size() <= 1) continue;
            int row = list.get(0).x;
            int col = list.get(0).y;
            int weight = 0;
            int speed = 0;
            int count = 0;
            int hol = 0;
            int jjak = 0;
            for (Node node : list) {
                die.add(node.num);
                weight += node.weight;
                speed += node.speed;
                if (node.direct % 2 != 0) hol++;
                else jjak++;
                count++;
            }

            if (weight < 5) continue;
            int[] nextDir = {0, 2, 4, 6};
            int nextWeight = weight / 5;
            int nextSpeed = speed / count;
            for (int i = 0; i < 4; i++) {
                int dir = -1;
                if (hol == count || jjak == count) dir = nextDir[i];
                else dir = nextDir[i] + 1;
                q.add(new Node(num++, row, col, nextWeight, nextSpeed, dir));
            }
        }
    }

    static void move(Node node) {

        node.x += dir[node.direct][0] * node.speed;
        node.y += dir[node.direct][1] * node.speed;

        while (node.x < 0 || node.x >= N) {
            if (node.x < 0) node.x += N;
            if (node.x >= N) node.x -= N;
        }
        while (node.y < 0 || node.y >= N) {
            if (node.y < 0) node.y += N;
            if (node.y >= N) node.y -= N;
        }

        if (map.containsKey(node)) {
            map.get(node).add(node);
            return;
        }
        map.put(node, new ArrayList<>());
        map.get(node).add(node);
    }


    static class Node {
        int num, x, y, weight, speed, direct;

        Node(int num, int x, int y, int weight, int speed, int direct) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.speed = speed;
            this.direct = direct;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", x=" + x +
                    ", y=" + y +
                    ", weight=" + weight +
                    ", speed=" + speed +
                    ", direct=" + direct +
                    '}';
        }
    }
}
