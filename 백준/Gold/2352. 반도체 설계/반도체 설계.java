import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {

    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if(i==0)  {
                list.add(cur);
                continue;
            }
            int last = list.get(list.size() - 1);
            if(last < cur) list.add(cur);
            else LIS(cur);
        }

        sb.append(list.size());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static public void LIS(int num) {
        int start = 0;
        int end = list.size() - 1;

        while(start < end) {
            int mid = (start + end) / 2;

            if(list.get(mid) >= num) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        list.set(end, num);
    }
}
