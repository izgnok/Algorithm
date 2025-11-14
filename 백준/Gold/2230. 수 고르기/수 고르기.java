import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);


        int result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            result = Math.min(result, binarySearch(i));
        }
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int binarySearch(int i) {

        int start = i + 1;
        int end = N - 1;
        int result = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = (start + end) / 2;
            int calc = arr[mid] - arr[i];

            if (calc >= M) {
                end = mid - 1;
                result = calc;
            } else start = mid + 1;
        }
        return result;
    }
}