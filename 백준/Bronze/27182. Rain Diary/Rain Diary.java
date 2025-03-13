import org.w3c.dom.Node;

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

        int maxDay = 0;
        if(N <= 14) {
            maxDay = M + (N - 14) * (-1);
        }
        M += 7;
        if (M > maxDay) M -= maxDay;
        int result = M;

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
