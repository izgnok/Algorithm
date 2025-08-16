import java.util.*;
import java.io.*;

public class Main {

    static HashMap<String, String> map;
    static HashMap<String, Integer> size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {

            int N = Integer.parseInt(br.readLine().trim());
            map = new HashMap<>();
            size = new HashMap<>();

            while (N-- > 0) {
                String cmd = br.readLine();
                String[] arr = cmd.split(" ");

                for (String str : arr) {
                    if (!map.containsKey(str)) {
                        map.put(str, str);
                        size.put(str, 1);
                    }
                }

                int result = union(arr[0], arr[1]);
                sb.append(result).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static String find(String str) {
        if (!map.get(str).equals(str)) map.put(str, find(map.get(str)));
        return map.get(str);
    }

    static int union(String a, String b) {
        a = find(a);
        b = find(b);
        if (a.equals(b)) return size.get(a);

        map.put(b, a);
        size.put(a, size.get(a) + size.get(b));

        return size.get(a);
    }
}