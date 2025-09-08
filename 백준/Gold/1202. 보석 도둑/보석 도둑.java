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
        int K = Integer.parseInt(st.nextToken());

        Node[] arr = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr[i] = new Node(w, cost);
        }
        int[] bag = new int[K];
        for (int i = 0; i < K; i++) bag[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr, (o1, o2) -> Integer.compare(o1.w, o2.w));
        Arrays.sort(bag);


        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        long sum = 0;
        int j = 0;
        for (int i = 0; i < K; i++) {
            while (j < N && arr[j].w <= bag[i]) {
                pq.add(arr[j].cost);
                j++;
            }
            if (!pq.isEmpty()) sum += pq.poll();
        }
        sb.append(sum);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Node {
        int w, cost;

        Node(int w, int cost) {
            this.w = w;
            this.cost = cost;
        }
    }
}