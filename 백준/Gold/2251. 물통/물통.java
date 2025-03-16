import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int A, B, C;
    static TreeSet<Integer> result;
    static HashSet<String> visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visit = new HashSet<>();
        result = new TreeSet<>();
        dfs(0, 0, C);

        for (int num : result) {
            sb.append(num).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    static void dfs(int a, int b, int c) {
        if (visitCheck(a, b, c)) return;
        if (a == 0) {
            result.add(c);
        }

        int restA = A - a;
        int restB = B - b;
        int restC = C - c;

        if (restA > 0) {
            // B -> A
            int next = Math.min(restA, b);
            dfs(a + next, b - next, c);

            // C -> A
            next = Math.min(restA, c);
            dfs(a + next, b, c - next);

        }

        if (restB > 0) {
            // A -> B
            int next = Math.min(restB, a);
            dfs(a - next, b + next, c);

            // C -> B
            next = Math.min(restB, c);
            dfs(a, b + next, c - next);

        }

        if (restC > 0) {
            // A -> C
            int next = Math.min(restC, a);
            dfs(a - next, b, c + next);

            // B -> C
            next = Math.min(restC, b);
            dfs(a, b - next, c + next);
        }
    }

    static boolean visitCheck(int a, int b, int c) {
        String key = a + "," + b + "," + c;
        if (visit.contains(key)) {
            return true;
        }
        visit.add(key);
        return false;
    }
}
