import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String S = br.readLine();
        String T = br.readLine();
        Deque<Character> dq = new ArrayDeque<>();
        for (char c : T.toCharArray()) dq.addLast(c);

        int dir = 1;
        while (dq.size() > S.length()) {
            if (dir == 1) {
                char c = dq.pollLast();
                if (c == 'B') dir *= -1;
            } else {
                char c = dq.pollFirst();
                if (c == 'B') dir *= -1;
            }
        }

        for (char c : S.toCharArray()) {
            if (dir == 1 && dq.peekFirst() == c) {
                dq.pollFirst();
            } else if (dir == -1 && dq.peekLast() == c) {
                dq.pollLast();
            } else break;
        }
        sb.append(dq.isEmpty() ? 1 : 0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}