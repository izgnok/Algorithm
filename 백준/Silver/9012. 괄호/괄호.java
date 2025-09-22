import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            String str = br.readLine();

            Deque<Character> stack = new ArrayDeque<>();
            boolean flag = true;
            for (char c : str.toCharArray()) {

                if (c == '(') {
                    stack.push(c);
                    continue;
                }

                if (stack.isEmpty() || stack.pollLast() != '(') {
                    flag = false;
                    break;
                }
            }
            sb.append(flag && stack.isEmpty() ? "YES" : "NO").append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}