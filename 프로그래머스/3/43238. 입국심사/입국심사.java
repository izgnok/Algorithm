import java.util.*;

class Solution {
    static int N;
    
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        int size = times.length;
        
        long start = 1;
        long end = times[size-1] * (long)n;
        long result = 0;
        while(start <= end) {
            long mid = (start + end) / 2;
            
            long check = 0;
            for(long time : times) {
                check += (mid / time);
            }

            if(check >= n) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }
}