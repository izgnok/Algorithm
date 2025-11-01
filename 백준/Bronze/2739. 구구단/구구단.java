import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= 9; i++) {
            sb.append(N).append(" * ").append(i).append(" = ").append(N * i).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}