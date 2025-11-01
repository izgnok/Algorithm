import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        boolean[] visit = new boolean[10001];
        for (int i = 1; i <= 10000; i++) {

            int next = i;
            String str = Integer.toString(i);
            for (char c : str.toCharArray()) next += (c - '0');
            if (next > 10000) continue;
            visit[next] = true;
        }
        for (int i = 1; i <= 10000; i++) if (!visit[i]) sb.append(i).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}