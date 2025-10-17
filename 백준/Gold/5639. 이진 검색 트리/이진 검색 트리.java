import java.io.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Node root = new Node(Integer.parseInt(br.readLine()));
        while (true) {
            String line = br.readLine();
            if (line == null || line.isBlank()) break;

            int num = Integer.parseInt(line);
            insert(root, num);
        }

        search(root);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void search(Node cur) {
        if (cur == null) return;
        search(cur.left);
        search(cur.right);
        sb.append(cur.num).append("\n");
    }

    public static void insert(Node cur, int num) {
        Node child = new Node(num);
        if (cur.num > num) {
            if (cur.left == null) cur.left = child;
            else insert(cur.left, num);
        } else {
            if (cur.right == null) cur.right = child;
            else insert(cur.right, num);
        }
    }

    public static class Node {
        int num;
        Node left;
        Node right;

        Node(int num) {
            this.num = num;
        }
    }
}

