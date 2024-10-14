import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        long q1_sum = 0;
        long q2_sum = 0;
        for(int i=0; i< queue1.length; i++) {
            q1.offer(queue1[i]);
            q1_sum += queue1[i];
            q2.offer(queue2[i]);
            q2_sum += queue2[i];
        }
        int answer = 0;
        int check = 0;
        while(q1_sum != q2_sum) {
            if(check == 2) break;
            
            if(q1_sum > q2_sum) {
                int num = q1.poll();
                q2.offer(num);
                q1_sum -= num;
                q2_sum += num;
            }
            else {
                int num = q2.poll();
                q1.offer(num);
                q1_sum += num;
                q2_sum -= num;
            }
            if(q1.isEmpty() || q2.isEmpty()) check++;
            answer++;
            if(answer > 600000) {
                answer = -1;
                break;
            }
        }
        
        if(check == 2) answer = -1;
        return answer;
    }
}