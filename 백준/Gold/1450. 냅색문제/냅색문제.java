import java.io.*;
import java.util.*;

public class Main {

    static int N, C;
    static List<Integer> input;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        list = new List[2];
        for (int i = 0; i < 2; i++) list[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        input = new ArrayList<>();
        for (int i = 0; i < N / 2; i++) input.add(Integer.parseInt(st.nextToken()));
        dfs(0, 0, 0);

        input = new ArrayList<>();
        for (int i = N / 2; i < N; i++) input.add(Integer.parseInt(st.nextToken()));
        dfs(1, 0, 0);

        Collections.sort(list[0]);
        Collections.sort(list[1]);

        long result = 0;
        for (int i = 0; i < list[0].size(); i++) {
            int cur = list[0].get(i);
            int start = 0;
            int end = list[1].size() - 1;

            while (start <= end) {
                int mid = (start + end) / 2;
                if (cur + list[1].get(mid) <= C) start = mid + 1;
                else end = mid - 1;
            }
            result += start;
        }

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int version, int depth, int size) {

        if (depth == input.size()) {
            list[version].add(size);
            return;
        }

        if (size + input.get(depth) <= C) {
            dfs(version, depth + 1, size + input.get(depth));
        }
        dfs(version, depth + 1, size);
    }
}