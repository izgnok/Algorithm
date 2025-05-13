import java.util.*;

class Solution {
    
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        
        for(int work: works) {
            pq.add(work);
        }
        
        while(!pq.isEmpty() && n--> 0) {
            int work = pq.poll();
            work--;
            if(work == 0) continue;
            pq.add(work);
        }
        
        long answer = 0;
        while(!pq.isEmpty()) {
            int work = pq.poll();
            answer += work * work;
        }
        
        return answer;
    }
}