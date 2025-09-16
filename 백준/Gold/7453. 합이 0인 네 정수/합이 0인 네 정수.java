import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        long[] listAB = new long[N * N];
        long[] listCD = new long[N * N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                listAB[idx] = (long) A[i] + B[j];
                listCD[idx] = (long) C[i] + D[j];
                idx++;
            }
        }

        Arrays.sort(listAB);
        Arrays.sort(listCD);

        int idx1 = 0;
        int idx2 = idx - 1;
        long result = 0;
        while (idx1 < listAB.length && idx2 >= 0) {

            long num1 = listAB[idx1];
            long num2 = listCD[idx2];

            if (num1 + num2 == 0) {
                int tmp1 = idx1;
                int tmp2 = idx2;
                while (idx1 < listAB.length && listAB[idx1] == num1) idx1++;
                while (idx2 >= 0 && listCD[idx2] == num2) idx2--;
                result += (long) (idx1 - tmp1) * (tmp2 - idx2);
            } else if (num1 + num2 > 0) {
                idx2--;
            } else idx1++;
        }

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}