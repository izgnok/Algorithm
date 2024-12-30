import java.util.*;

class Solution {
    public int solution(int[] citations) {
        
        int size = citations.length;
        Arrays.sort(citations);
        
        int start = 1;
        int end = size; 
        int answer = 0;
        while(start <= end) {
            int mid = (start + end) / 2;
            
            if(citations[size - mid] >= mid) {
                start = mid + 1;
                answer = mid;
            } else {
                end = mid - 1;
            }
        }    
        return answer;
    }
}