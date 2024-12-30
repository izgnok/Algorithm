import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        
        int size = progresses.length;
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0; i<size; i++) {
            int rest = 100 - progresses[i];
            
            int day = rest / speeds[i];
            if(rest % speeds[i] != 0) day++;
            
            q.add(day);
        }
        
        List<Integer> answer = new ArrayList<>();
        int count = 0;
        int max = q.peek();
    
        while(!q.isEmpty()) {
            if(max >= q.peek()) {
                count++;
                q.poll();
                
                if(q.isEmpty()) answer.add(count);
            }
            else {
                answer.add(count);
                max = q.peek();
                count = 0;
            }
        }
        return answer;
    }
}