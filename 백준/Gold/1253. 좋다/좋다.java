import java.util.*;
import java.io.*;

public class Main {

    static int N;


    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int count = getCount(arr);
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int getCount(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;
        for(int i=0; i<N; i++) {
//            if(set.contains(arr[i])) {
//                count++;
//                continue;
//            }
            int left = 0;
            int right = N - 1;
            while(left < right) {
                if(i == right) right--;
                if(i == left) left++;
                if(left >= right) break;

                int num = arr[left] + arr[right];
//                set.add(num);

                if(num > arr[i]) {
                    right--;
                }
                else if(num < arr[i]) {
                    left++;
                }
                else {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}