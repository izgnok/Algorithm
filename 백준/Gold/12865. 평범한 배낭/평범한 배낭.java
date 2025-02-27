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
        int K = Integer.parseInt(st.nextToken());


        Node[] list = new Node[N];
        int[] DP = new int[K + 1];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[i] = new Node(cost, weight);
        }
        
        for (int i = 0; i < N; i++) {
            for (int k = K; k >= list[i].weight; k--) {
                DP[k] = Math.max(DP[k], DP[k - list[i].weight] + list[i].cost);
            }
        }
        
        sb.append(DP[K]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Node {
        int cost, weight;

        Node(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }
    }
}