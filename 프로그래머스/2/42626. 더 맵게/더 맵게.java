import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int num: scoville) {
            pq.add(num);
        }
        
        int answer = 0;
        while(pq.peek() < K) {
            
            int first = pq.poll();
            if(pq.isEmpty()) {
                answer = -1;
                break;
            }
            int second = pq.poll();
            int mix = first + (second * 2);
            
            pq.add(mix);
            answer++;
        }
        
        return answer;
    }
}