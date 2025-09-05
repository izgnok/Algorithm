import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {


    static TrieNode root;
    static String[] arr = {"A", "C", "G", "T"};
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine().trim();
        int N = str.length();
        root = new TrieNode();
        for (int i = 0; i < N; i++) {
            insert(str);
            str = str.substring(1);
        }

        result = null;
        for (int i = 1; i <= 6; i++) {
            dfs(i, 0, new int[i]);
            if (result != null) break;
        }
        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
        boolean isEnd = false;
    }

    static void insert(String str) {
        TrieNode node = root;
        for (char c : str.toCharArray()) {
            if (!node.child.containsKey(c)) node.child.put(c, new TrieNode());
            node = node.child.get(c);
        }
        node.isEnd = true;
    }

    static boolean prefix(String str) {
        TrieNode node = root;
        for (char c : str.toCharArray()) {
            if (!node.child.containsKey(c)) return true;
            node = node.child.get(c);
        }
        return false;
    }

    static void dfs(int k, int depth, int[] check) {
        if (depth == k) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < k; i++) sb.append(arr[check[i]]);
            if (prefix(sb.toString())) result = sb.toString();
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (result != null) break;
            check[depth] = i;
            dfs(k, depth + 1, check);
        }
    }
}

