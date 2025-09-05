import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static HashMap<Long, Long> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new HashMap<>();
        map.put(0L, 1L);
        long sum = 0;
        long result = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sum += Integer.parseInt(st.nextToken());

            if (map.containsKey(sum - K)) result += map.get(sum - K);
            map.put(sum, map.getOrDefault(sum, 0L) + 1);
        }

        sb.append(result).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}