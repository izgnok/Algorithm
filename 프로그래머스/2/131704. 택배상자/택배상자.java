import java.util.*;

class Solution {
    public int solution(int[] order) {
        
        
        Deque<Integer> dq = new ArrayDeque<>();
        HashSet<Integer> set = new HashSet<>();
        
        int conveyer = 1;
        int answer = 0;
        for(int i=0; i<order.length; i++) {
            
            if(conveyer == order[i]) {
                answer++;
                conveyer++;
                continue;
            }
            
            if(!dq.isEmpty() && dq.peekLast() == order[i]) {
                answer++;
                dq.pollLast();
                continue;
            }
            
            if(set.contains(order[i])) break;
            dq.addLast(conveyer);
            set.add(conveyer);
            conveyer++;
            i--;
        }
        return answer;
    }
}