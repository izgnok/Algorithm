import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static int[] visit;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());


        int T = Integer.parseInt(st.nextToken());
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            visit = new int[N + 1];
            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                graph.get(v1).add(v2);
                graph.get(v2).add(v1);
            }

            boolean result = true;
            for (int i = 1; i <= N; i++) {
                if (visit[i] != 0) continue;
                if (!bfs(i)) {
                    result = false;
                    break;
                }
            }

            String answer = result ? "YES\n" : "NO\n";
            sb.append(answer);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean bfs(int num) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(num, 1));
        visit[num] = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int next : graph.get(cur.num)) {
                if (visit[next] == cur.color) return false;
                if (visit[next] != 0) continue;
                int color = cur.color * -1;
                visit[next] = color;
                q.add(new Node(next, color));
            }
        }
        return true;
    }

    static class Node {
        int num, color;

        Node(int num, int color) {
            this.num = num;
            this.color = color;
        }
    }
}
