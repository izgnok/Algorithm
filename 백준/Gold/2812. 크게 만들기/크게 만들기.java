import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String str = br.readLine();

        int size = N - K;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addLast(str.charAt(0) - '0');
        for (int i = 1; i < N; i++) {
            int num = str.charAt(i) - '0';
            while (!dq.isEmpty() && dq.size() + N - i > size && dq.peekLast() < num) dq.pollLast();
            dq.addLast(num);
        }
        
        for(int i=0; i<size; i++) sb.append(dq.pollFirst());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}