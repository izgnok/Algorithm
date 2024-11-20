import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static int N, C;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int start = 1;
        int end = arr[N - 1] - arr[0];
        int result = 0;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(simulation(mid)) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean simulation(int dist) { // 공유기 설치
        int cnt = C - 1;

        int pre = 0;
        for(int i=1; i < N; i++) {
            if(cnt == 0) break;
            if(arr[i] - arr[pre] >= dist) {
                cnt--;
                pre = i;
            }
        }
        return cnt == 0;
    }
}