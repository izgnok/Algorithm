import  java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());

        long[] wait = new long[N];
        long max = 0L;
        for(int i = 0; i<N; i++) {
            wait[i] = Long.parseLong(br.readLine().trim());
            max = Math.max(wait[i], max);
        }

        long start = 1L;
        long end = max * M;
        Long answer = null;
        while(start <= end) {
            long mid = (start + end) / 2;

            long count = 0;
            for(int i=0; i<N; i++) {
                count += mid / wait[i];
                if(count >= M) break;
            }

            if(count >= M) {
                answer = mid;
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
 }