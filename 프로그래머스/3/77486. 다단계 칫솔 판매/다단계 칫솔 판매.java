import java.util.*;

class Solution {
    
    static Map<String, String> parent;
    static Map<String, Integer> profit;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        parent = new HashMap<>();
        profit = new HashMap<>();
        
        int N = enroll.length;
        for(int i=0; i<N; i++) {
            String name = enroll[i];
            parent.put(name, "center");
            profit.put(name, 0);
            
            if(referral[i].equals("-")) continue;
            parent.put(name, referral[i]);
        }
        
        int size = seller.length;
        for(int i=0; i<size; i++) {
            String name = seller[i];
            int price = amount[i] * 100;
            
            calc(name, price);
        }
        
        int[] answer = new int[N];
        for(int i=0; i<N; i++) {
            answer[i] = profit.get(enroll[i]);
        }
        return answer;
    }
    
    static void calc(String name, int price) {
       if(name.equals("center")) return;
        
        
        int nextPrice = (int) (price * 0.1);
        int myPrice = price - nextPrice;
        profit.put(name, profit.get(name) + myPrice);
        
        if(nextPrice == 0) return;
        calc(parent.get(name), nextPrice);
    }
}