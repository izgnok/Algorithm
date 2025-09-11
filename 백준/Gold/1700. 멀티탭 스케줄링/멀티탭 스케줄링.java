import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        Map<Integer, Queue<Integer>> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[K];
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayDeque<>());
            }

            map.get(arr[i]).add(i);
        }

        int result = 0;
        for (int i = 0; i < K; i++) {
            map.get(arr[i]).poll();
            if (set.size() < N || set.contains(arr[i])) {
                set.add(arr[i]);
                continue;
            }

            int max = -1;
            int idx = -1;
            for (int key : set) {
                Queue<Integer> q = map.get(key);
                if (q.isEmpty()) {
                    idx = key;
                    break;
                }

                if (max < q.peek() ) {
                    max = q.peek();
                    idx = key;
                }
            }
            set.remove(idx);
            set.add(arr[i]);
            result++;
        }
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}