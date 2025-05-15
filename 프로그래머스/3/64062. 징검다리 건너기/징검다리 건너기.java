import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int n = stones.length;
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            while (!dq.isEmpty() && stones[dq.peekLast()] <= stones[i]) {
                dq.pollLast();
            }
            dq.addLast(i);

            if (dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            if (i >= k - 1) {
                answer = Math.min(answer, stones[dq.peekFirst()]);
            }
        }

        return answer;
    }
}
