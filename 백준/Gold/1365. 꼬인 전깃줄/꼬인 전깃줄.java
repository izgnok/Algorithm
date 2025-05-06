import java.io.*;
import java.util.*;

public class Main {

    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        int count = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (list.isEmpty() || list.get(list.size() - 1) < num) list.add(num);
            else {
                int idx = binarySearch(num);
                list.set(idx, num);
                count++;
            }
        }
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int binarySearch(int num) {
        int start = 0;
        int end = list.size() - 1;

        while (start < end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < num) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }
}
