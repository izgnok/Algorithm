import java.util.*;

class Solution {
    public int solution(int[] topping) {
        
        int size = topping.length;
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        
        left.put(topping[0], 1);
        for(int i=1; i<size; i++) {
            if(!right.containsKey(topping[i])) {
                right.put(topping[i], 1);
            }
            else right.put(topping[i], right.get(topping[i]) + 1);
        }
        
        int answer = 0;
        if(left.size() == right.size()) answer++;
        for(int i=1; i<size - 1; i++) {
            if(left.size() > right.size()) break;
            
            left.put(topping[i], 1);
            right.put(topping[i], right.get(topping[i]) - 1);
            if(right.get(topping[i]) == 0) right.remove(topping[i]);
            
            if(left.size() == right.size()) answer++;
        }
        
        return answer;
    }
}