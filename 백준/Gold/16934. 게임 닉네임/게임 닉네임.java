import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    static TrieNode root;
    static HashMap<String, Integer> map;

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        root = new TrieNode();
        while (N-- > 0) {
            String str = br.readLine();
            map.put(str, map.getOrDefault(str, 0) + 1);

            String result = insert(str);
            sb.append(result);
            if (result.equals(str)) sb.append(map.get(result) == 1 ? "" : map.get(result));
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class TrieNode {
        Map<Character, TrieNode> child = new TreeMap<>();
        boolean isEnd = false;
    }

    static String insert(String str) {
        TrieNode node = root;
        StringBuilder sb = new StringBuilder();

        boolean check = true;
        for (char c : str.toCharArray()) {
            if (check) sb.append(c);
            if (!node.child.containsKey(c)) {
                node.child.put(c, new TrieNode());
                check = false;
            }
            node = node.child.get(c);
        }
        node.isEnd = true;
        return sb.toString();
    }


}

