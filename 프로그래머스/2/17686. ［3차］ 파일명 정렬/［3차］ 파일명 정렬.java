import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        
        List<Node> list = new ArrayList<>();
        for(String file: files) {
            boolean isNum = false;
            String head = "";
            String number = "";
            for(char c: file.toCharArray()) {
                if(!isNum) {
                    if (c >= '0' && c <= '9') {
                        isNum = true;
                        number += c;
                        continue;
                    }
                    head += c;
                }
                if(isNum) {
                    if (c < '0' || c > '9') {
                        isNum = false;
                        break;
                    }
                    number += c;
                }
            }
            list.add(new Node(file, head, number));
        }
        
        Collections.sort(list, (o1, o2) -> {
            String h1 = o1.head.toLowerCase();
            String h2 = o2.head.toLowerCase();
            if (h1.equals(h2)) {
                return Integer.parseInt(o1.number) - Integer.parseInt(o2.number);
            }
            return h1.compareTo(h2);
        });

        
        String[] answer = new String[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i).file;
        }
        return answer;
    }
    
    static class Node {
        String file, head, number;
        
        Node(String file, String head, String number) {
            this.file = file;
            this.head = head;
            this.number = number;
        }
    }
}