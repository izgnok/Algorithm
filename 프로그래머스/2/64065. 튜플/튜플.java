import java.util.*;

class Solution {
    public int[] solution(String s) {
    
        List<Node> list = new ArrayList<>();
        boolean start = false;
        String str = "";
        int count = 0;
        for(char c: s.toCharArray()) {
            if(!start && c != '{') continue;
            if(c == '{') {
                start = true;
                continue;
            }
            if(c == '}') {
                start = false;
                list.add(new Node(str, count));
                str = "";
                count = 0;
                continue;
            }
            if(c == ',') count++;
            str += c;
        }    
        
        Collections.sort(list, (o1, o2) -> o1.count - o2.count);
        HashSet<String> set = new HashSet<>();
        int size = list.size();
        int[] answer = new int[size];
        for(int i=0; i<size; i++) {
            String[] strArr = list.get(i).str.split(",");
            
            for(String num: strArr) {
                if(set.contains(num)) continue;
                set.add(num);
                answer[i] = Integer.parseInt(num);
            }
        }
        return answer;
    }
    
    static class Node {
        String str;
        int count;
        
        Node(String str, int count) {
            this.str = str;
            this.count = count;
        }
        
        @Override
        public String toString() {
            return "문자: " + this.str + ", 개수: " + this.count;
        }
    }
}