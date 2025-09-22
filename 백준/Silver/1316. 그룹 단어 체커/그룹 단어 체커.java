import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int result = 0;

        while (N-- > 0) {
            String str = br.readLine();

            boolean flag = true;
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < str.length(); i++) {

                char c = str.charAt(i);
                if (set.contains(c)) {
                    flag = false;
                    break;
                }

                set.add(c);
                while (i + 1 < str.length() && str.charAt(i + 1) == c) i++;
            }
            if (flag) result++;
        }
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}