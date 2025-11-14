import java.util.*;

class Solution {
    
    static Map<String, Node> map;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        map = new HashMap<>();
        
        int N = enroll.length;
        for(int i=0; i<N; i++) {
            String name = enroll[i];
            Node node = new Node("center", 0);
            if(!referral[i].equals("-")) node.parent = referral[i];
            map.put(name, node);
        }
        
        int size = seller.length;
        for(int i=0; i<size; i++) {
            String name = seller[i];
            int price = amount[i] * 100;
            
            calc(name, price);
        }
        
        int[] answer = new int[N];
        for(int i=0; i<N; i++) {
            answer[i] = map.get(enroll[i]).profit;
        }
        return answer;
    }
    
    static void calc(String name, int price) {
       if(name.equals("center")) return;
        
        Node node = map.get(name);
        
        int nextPrice = (int) (price * 0.1);
        node.profit += (price - nextPrice);
        
        if(nextPrice == 0) return;
        calc(node.parent, nextPrice);
    }
    
    static class Node {
        String parent;
        int profit;
        
        Node(String parent, int profit) {
            this.parent = parent;
            this.profit = profit;
        }
    }
}