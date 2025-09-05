import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    static TrieNode root;
    static StringBuilder sb;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        root = new TrieNode();
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());

            String[] arr = new String[M];
            for (int i = 0; i < M; i++) arr[i] = st.nextToken();
            insert(arr);
        }

        search(root, 0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class TrieNode {
        Map<String, TrieNode> child = new TreeMap<>();
        boolean isEnd = false;
    }

    static void insert(String[] strArr) {
        TrieNode node = root;
        for (String str : strArr) {
            if (!node.child.containsKey(str)) node.child.put(str, new TrieNode());
            node = node.child.get(str);
        }
        node.isEnd = true;
    }


    static void search(TrieNode node, int depth) {

        for (String str : node.child.keySet()) {
            for (int i = 0; i < depth; i++) sb.append("--");
            sb.append(str).append("\n");

            search(node.child.get(str), depth + 1);
        }
    }
}

