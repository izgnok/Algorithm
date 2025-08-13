import java.util.*;
import java.io.*;

public class Main {

    static int[] parent;
    static int[] truth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        truth = new int[K];
        for (int i = 0; i < K; i++) {
            truth[i] = Integer.parseInt(st.nextToken());
        }


        List<int[]> list = new ArrayList<>();
        for (int party = 0; party < M; party++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int[] arr = new int[cnt];
            list.add(arr);

            for (int i = 0; i < cnt; i++) arr[i] = Integer.parseInt(st.nextToken());
            for (int i = 1; i < cnt; i++) union(arr[0], arr[i]);
        }


        int result = 0;
        for (int[] arr : list) {
            boolean check = true;
            for (int a : arr) {
                for (int t : truth) {
                    if (find(a) == find(t)) {
                        check = false;
                        break;
                    }
                }
                if (!check) break;
            }
            if (check) result++;
        }

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    public static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }
}