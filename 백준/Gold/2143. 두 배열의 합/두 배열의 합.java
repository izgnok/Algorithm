import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int T, N, M;
    static HashMap<Integer, Integer> mapA;
    static HashMap<Integer, Integer> mapB;

    static int[] A;
    static int[] B;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine().trim());
        N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine());
        B = new int[M];
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        mapA = new HashMap<>();
        mapB = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += A[j];
                mapA.put(sum, mapA.getOrDefault(sum, 0) + 1);
            }
        }

        for (int i = 0; i < M; i++) {
            int sum = 0;
            for (int j = i; j < M; j++) {
                sum += B[j];
                mapB.put(sum, mapB.getOrDefault(sum, 0) + 1);
            }
        }

        long result = 0;
        for (int sum : mapA.keySet()) {
            int countA = mapA.get(sum);
            int countB = mapB.getOrDefault(T - sum, 0);
            result += (long) countA * countB;
        }
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
