import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            long n = numbers[i];
            
            if (n % 2 == 0) {
                answer[i] = n + 1;
            } 
            else {
                long cur = 1;
                while ((n & cur) != 0) {
                    cur *= 2;
                }
                answer[i] = n + cur - (cur / 2);
            }
        }
        return answer;
    }
}
