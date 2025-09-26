import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int atk = Integer.parseInt(st.nextToken());

        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(a, b, c);
        }

        long start = 1;
        long end = 987654321987654321L;
        while (start <= end) {
            long mid = (start + end) / 2;
            boolean check = simulation(nodes, mid, atk);
            if (check) end = mid - 1;
            else start = mid + 1;
        }
        sb.append(end + 1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static boolean simulation(Node[] nodes, long maxHP, long atk) {

        long curHP = maxHP;
        for (Node node : nodes) {
            if(node.potion == 2) {
                curHP = Math.min(maxHP, curHP + node.hp);
                atk += node.atk;
                continue;
            }

            long hero = node.hp / atk;
            if (node.hp % atk != 0) hero++;
            long monster = hero - 1;
            curHP -= monster * node.atk;
            if (curHP <= 0) return false;
        }
        return true;
    }

    static class Node {
        int potion, atk, hp;

        Node(int potion, int atk, int hp) {
            this.potion = potion;
            this.atk = atk;
            this.hp = hp;
        }
    }
}