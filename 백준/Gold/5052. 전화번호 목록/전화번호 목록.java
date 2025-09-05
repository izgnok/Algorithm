import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

public class Main {


    static TrieNode root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            root = new TrieNode();
            int N = Integer.parseInt(br.readLine());
            String[] arr = new String[N];

            for (int i = 0; i < N; i++) arr[i] = br.readLine().trim();
            Arrays.sort(arr);

            boolean check = false;
            for (int i = N - 1; i >= 0; i--) {
                check = PrefixSearch(arr[i]);
                if (check) break;
                insert(arr[i]);
            }
            sb.append(!check ? "YES" : "NO").append("\n");
        }
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

    static boolean PrefixSearch(String str) {
        TrieNode node = root;
        for (char c : str.toCharArray()) {
            if (!node.child.containsKey(c)) return false;
            node = node.child.get(c);
        }
        return true;
    }
}