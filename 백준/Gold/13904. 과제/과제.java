import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        Node[] arr = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(arr, (o1, o2) -> o1.day - o2.day);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        for (int i = 0; i < N; i++) {
            pq.add(arr[i]);
            if (pq.size() > arr[i].day) pq.poll();
        }

        int result = 0;
        while (!pq.isEmpty()) result += pq.poll().cost;
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Node {
        int day, cost;

        Node(int day, int cost) {
            this.day = day;
            this.cost = cost;
        }
    }
}