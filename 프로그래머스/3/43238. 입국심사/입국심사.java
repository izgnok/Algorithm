import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        int size = times.length;
        
        long start = 1;
        long end = times[size - 1] * (long) n;
        
        while(start <= end) {
            long mid = (start + end) / 2;
            
            long check = 0;
            for(int time: times) {
                check += (mid / time);
                if(check >= n) break;
            }
            
            if(check >= n) end = mid - 1;
            else start = mid + 1;
        }
        return end + 1;
    }
}