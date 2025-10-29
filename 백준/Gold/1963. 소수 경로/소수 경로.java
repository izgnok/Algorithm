import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static boolean[] isPrime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        isPrime = new boolean[10000];
        sosuCheck();

        StringTokenizer st;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            String end = st.nextToken().trim();

            int result = bfs(start, end);
            sb.append(result == -1 ? "Impossible" : result).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static public int bfs(int start, String end) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(Integer.toString(start), 0));

        boolean[] visit = new boolean[10000];
        visit[start] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (end.equals(node.num)) return node.count;

            String str = node.num;
            int cur = Integer.parseInt(str);
            for (int k = 0; k < 4; k++) {
                int num = str.charAt(k) - '0';
                cur -= num * (int) Math.pow(10, 3 - k);
                for (int j = 1; j <= 9; j++) {
                    int tmp = (num + j) % 10;
                    cur += tmp * (int) Math.pow(10, 3 - k);
                    if (cur >= 1000 && isPrime[cur] && !visit[cur]) {
                        q.add(new Node(Integer.toString(cur), node.count + 1));
                        visit[cur] = true;
                    }
                    cur -= tmp * (int) Math.pow(10, 3 - k);
                }
                cur += num * (int) Math.pow(10, 3 - k);
            }
        }
        return -1;
    }

    static void sosuCheck() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i < Math.sqrt(10000); i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < 10000; j += i) {
                isPrime[j] = false;
            }
        }
    }

    static class Node {
        int count;
        String num;

        Node(String num, int count) {
            this.num = num;
            this.count = count;
        }
    }

}