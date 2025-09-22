import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int N;
    static int[] count;
    static char[] result;
    static StringBuilder sb = new StringBuilder();
    static Map<String, Boolean> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine().trim();
        N = str.length();
        result = new char[N];
        count = new int[3];
        for (char c : str.toCharArray()) count[c - 'A']++;

        map = new HashMap<>();
        if (!dfs(0, ' ', ' ')) sb.append("-1");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean dfs(int depth, char last1, char last2) {
        String key = count[0] + "," + count[1] + "," + count[2] + "," + last1 + "," + last2;
        if (!map.getOrDefault(key, true)) return false;

        if (depth == N) {
            for (char c : result) sb.append(c);
            return true;
        }

        boolean flag = false;

        // C
        if (count[2] > 0 && last1 != 'C' && last2 != 'C') {
            count[2]--;
            result[depth] = 'C';
            flag = dfs(depth + 1, 'C', last1);
            count[2]++;
        }

        // B
        if (!flag && count[1] > 0 && last1 != 'B') {
            count[1]--;
            result[depth] = 'B';
            flag = dfs(depth + 1, 'B', last1);
            count[1]++;
        }

        // A
        if (!flag && count[0] > 0) {
            count[0]--;
            result[depth] = 'A';
            flag = dfs(depth + 1, 'A', last1);
            count[0]++;
        }

        map.put(key, flag);
        return flag;
    }
}