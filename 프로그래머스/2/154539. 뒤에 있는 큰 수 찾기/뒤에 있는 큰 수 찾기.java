import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            int now = numbers[i];

            while (!dq.isEmpty() && dq.peekLast() <= now) {
                dq.pollLast();
            }
            
            answer[i] = dq.isEmpty() ? -1 : dq.peekLast();
            dq.addLast(now);
        }

        return answer;
    }
}
