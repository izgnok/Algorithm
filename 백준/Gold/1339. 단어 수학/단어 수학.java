import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int size = 0;
        HashMap<Character, Integer> scoreMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        while (N-- > 0) {
            String str = br.readLine();
            list.add(str);
            size = Math.max(size, str.length());
        }

        for (int i = size; i > 0; i--) {
            for (String str : list) {
                if (str.length() < i) continue;
                char c = str.charAt(str.length() - i);
                scoreMap.put(c, scoreMap.getOrDefault(c, 0) + (int) Math.pow(10, i));
            }
        }

        List<Node> scoreList = new ArrayList<>();
        for (char c : scoreMap.keySet()) {
            scoreList.add(new Node(c, scoreMap.get(c)));
        }
        Collections.sort(scoreList, (o1, o2) -> Integer.compare(o2.score, o1.score));


        int num = 9;
        HashMap<Character, Integer> charToNum = new HashMap<>();
        for (Node node : scoreList) {
            if (charToNum.containsKey(node.c)) continue;
            charToNum.put(node.c, num--);
        }

        int result = 0;
        for (String str : list) {
            int sum = 0;
            for (int i = 0; i < str.length(); i++) {
                sum += (int) (charToNum.get(str.charAt(i)) * Math.pow(10, str.length() - i - 1));
            }
            result += sum;
        }

        sb.append(result).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Node {
        char c;
        int score = 0;

        Node(char c, int score) {
            this.c = c;
            this.score = score;
        }
    }
}