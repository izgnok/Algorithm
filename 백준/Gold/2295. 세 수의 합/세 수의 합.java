import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] arr;
    static HashSet<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        set = new HashSet<>();
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                set.add(arr[i] + arr[j]);
            }
        }

        int result = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (set.contains(arr[i] - arr[j])) {
                    result = arr[i];
                    break;
                }
            }
            if (result != 0) break;
        }

        sb.append(result).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}