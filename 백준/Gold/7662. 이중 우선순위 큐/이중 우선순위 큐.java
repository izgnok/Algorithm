import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            TreeMap<Integer, Integer> tree = new TreeMap<>();
            int Q = Integer.parseInt(br.readLine().trim());
            while (Q-- > 0) {
                String str = br.readLine().trim();
                String[] op = str.split(" ");
                int num = Integer.parseInt(op[1]);
                if (op[0].equals("I")) {
                    tree.put(num, tree.getOrDefault(num, 0) + 1);
                } else {
                    if (tree.isEmpty()) continue;

                    int key = (num == -1) ? tree.firstKey() : tree.lastKey();

                    if (tree.get(key) > 1) {
                        tree.put(key, tree.get(key) - 1);
                    } else {
                        tree.remove(key);
                    }
                }
            }
            if (tree.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(tree.lastKey()).append(" ").append(tree.firstKey()).append("\n");
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}