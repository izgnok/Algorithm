import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i < N; i++) list.add(Integer.parseInt(br.readLine()));
        Collections.sort(list);

        int start = 1;
        int end = list.get(N - 1) - list.get(0);
        while (start <= end) {
            int mid = (start + end) / 2;
            if (simulation(mid, K - 1)) start = mid + 1;
            else end = mid - 1;
        }
        sb.append(start - 1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean simulation(int dist, int k) {
        int pre = list.get(0);
        for (int i = 1; i < N; i++) {
            if (dist > list.get(i) - pre) continue;
            pre = list.get(i);
            if (--k == 0) break;
        }
        return k == 0;
    }
}