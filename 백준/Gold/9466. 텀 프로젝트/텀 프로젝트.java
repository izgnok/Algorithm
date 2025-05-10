import java.io.*;
import java.util.*;

public class Main {

    static int[] arr;
    static boolean[] visited, finished;
    static int N, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            visited = new boolean[N + 1];
            finished = new boolean[N + 1];
            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                if (!visited[i]) dfs(i);
            }

            sb.append(N - count).append("\n"); // 팀 못된 학생 수
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static void dfs(int cur) {
        visited[cur] = true;
        int next = arr[cur];

        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            // 사이클 시작
            for (int i = next; i != cur; i = arr[i]) {
                count++;
            }
            count++; // 자기 자신
        }

        finished[cur] = true;
    }
}
