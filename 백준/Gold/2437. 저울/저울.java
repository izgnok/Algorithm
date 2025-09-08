import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        int end = arr[0];
        if (end > 1) sb.append("1");
        else {
            for (int i = 1; i < N; i++) {
                if (arr[i] > end + 1) break;
                end = end + arr[i];
            }
            sb.append(end + 1);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}