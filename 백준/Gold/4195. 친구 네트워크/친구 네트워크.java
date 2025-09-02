import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static Map<String, String> parent;
    static Map<String, Integer> count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());

            parent = new HashMap<>();
            count = new HashMap<>();

            while (N-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String x = st.nextToken();
                String y = st.nextToken();

                if (!parent.containsKey(x)) {
                    parent.put(x, x);
                    count.put(x, 1);
                }
                if (!parent.containsKey(y)) {
                    parent.put(y, y);
                    count.put(y, 1);
                }

                int result = union(x, y);
                sb.append(result).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static String find(String x) {
        if (!x.equals(parent.get(x))) parent.put(x, find(parent.get(x)));
        return parent.get(x);
    }

    static int union(String x, String y) {
        x = find(x);
        y = find(y);
        if (x.equals(y)) return count.get(x);

        parent.put(y, x);
        count.put(x, count.get(x) + count.get(y));

        return count.get(x);
    }
}