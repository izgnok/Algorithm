import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String cmd = br.readLine().trim();

            int N = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[N];
            String str = br.readLine().trim();
            str = str.replace("[", "");
            str = str.replace("]", "");

            String[] tmp = str.split(",");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(tmp[i]);
            }

            int dir = 1;
            int left = 0;
            int right = N - 1;
            boolean error = false;
            boolean empty = N == 0 ? true : false;
            for (char c : cmd.toCharArray()) {
                if (c == 'R') dir *= -1;
                else {
                    if (empty) {
                        error = true;
                        break;
                    }

                    if (dir == 1) left++;
                    else right--;

                    if (left > right) empty = true;
                }
            }

            if (error) sb.append("error\n");
            else if (empty) sb.append("[]\n");
            else {
                sb.append("[");
                while (true) {
                    if (dir == 1) sb.append(arr[left++]);
                    else sb.append(arr[right--]);

                    if (left <= right) sb.append(",");
                    else break;
                }
                sb.append("]\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}