import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        int answer = 0;
        for(int e: enemy) {
            n -= e;
            pq.add(e);
            while(n < 0) {
                if(pq.isEmpty() || k == 0) break;
                n += pq.poll();
                k--;
            }
            if(n < 0) break;
            answer++;
        }
        
        return answer;
    }
}