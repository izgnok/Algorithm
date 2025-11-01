import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (i < 10) {
                result++;
                continue;
            }

            String str = Integer.toString(i);
            int standard = Calc(str, 1, 0);
            boolean check = true;

            for (int j = 2; j < str.length(); j++) {
                if (Calc(str, j, j - 1) == standard) continue;
                check = false;
                break;
            }
            if (check) result++;
        }

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int Calc(String str, int i, int j) {
        return (str.charAt(i) - '0') - (str.charAt(j) - '0');
    }

}