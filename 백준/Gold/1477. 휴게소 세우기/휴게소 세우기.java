import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 2];
        arr[0] = 0;
        arr[N + 1] = L;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int start = 1;
        int end = L;
        int answer = 0;
        while (start <= end) {
            int mid = (start + end) / 2;

            int k = M;
            boolean check = true;
            for (int i = 1; i <= N + 1; i++) {
                int dist = arr[i] - arr[i - 1];

                if(mid < dist) {
                    k -= (dist / mid);
                    if(dist % mid == 0) k++;
                }
                if (k < 0) {
                    check = false;
                    break;
                }
            }

            if (check) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}