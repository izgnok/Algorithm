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

        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num >= 0) list.add(num);
            else list2.add(num * -1);
        }
        list.sort(Comparator.reverseOrder());
        list2.sort(Comparator.reverseOrder());

        int result = 0;
        for (int i = 0; i < list.size(); i += K) result += list.get(i) * 2;
        for (int i = 0; i < list2.size(); i += K) result += list2.get(i) * 2;
        result -= Math.max(list.isEmpty() ? 0 : list.get(0), list2.isEmpty() ? 0 : list2.get(0));

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}