import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int size = A.length;
        int answer = 0;
        
        int start = 0;
        int end = size - 1;
        for(int i=size -1; i>=0; i--) {
            if(A[i] < B[end]) {
                answer++;
                end--;
            }
            else {
                start++;
            }
            
            if(start > end) break;
        }
        return answer;
    }
}