import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        Set<String> set = new HashSet<>();
        set.add("c=");
        set.add("c-");
        set.add("d-");
        set.add("dz=");
        set.add("lj");
        set.add("nj");
        set.add("s=");
        set.add("z=");

        int result = 0;
        for (int i = 0; i < str.length(); i++) {

            StringBuilder tmp = new StringBuilder();
            if (i + 1 < str.length()) {
                tmp.append(str.charAt(i));
                tmp.append(str.charAt(i + 1));
                if (set.contains(tmp.toString())) {
                    result++;
                    i++;
                    continue;
                }
            }

            if (i + 2 < str.length()) {
                tmp.append(str.charAt(i + 2));
                if (set.contains(tmp.toString())) {
                    result++;
                    i += 2;
                    continue;
                }
            }

            result++;
        }
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}