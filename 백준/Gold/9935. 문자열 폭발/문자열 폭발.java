import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String bomb = br.readLine();

        int m = bomb.length();
        char[] p = bomb.toCharArray();
        Deque<Character> dq = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) {
            dq.addLast(str.charAt(i));

            if (dq.size() >= m) {
                List<Character> list = new ArrayList<>();
                boolean match = true;

                for (int j = m - 1; j >= 0; j--) {
                    char c = dq.pollLast();
                    list.add(c);
                    if (c != p[j]) {
                        match = false;
                        break;
                    }
                }
                if (match) continue;
                for (int j = list.size() - 1; j >= 0; j--) {
                    dq.addLast(list.get(j));
                }
            }
        }

        if (dq.isEmpty()) sb.append("FRULA");
        else {
            while (!dq.isEmpty()) {
                sb.append(dq.pollFirst());
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}