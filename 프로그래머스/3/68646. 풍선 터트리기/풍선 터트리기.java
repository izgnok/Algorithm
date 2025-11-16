import java.util.*;

class Solution {
    public int solution(int[] a) {
        
        int N = a.length;
        int[] left = new int[N];
        int[] right = new int[N];
        
        left[0] = Integer.MAX_VALUE;
        for(int i=1; i<N; i++) {
            left[i] = Math.min(left[i - 1], a[i - 1]);
        }
        
        
        right[N - 1] = Integer.MAX_VALUE;
        for(int i=N-2; i>=0; i--) {
            right[i] = Math.min(right[i + 1], a[i + 1]);
        }
        
        int answer = 0;
        for(int i=0; i<N; i++) {
            if(a[i] <= left[i] || a[i] <= right[i]) answer++;
        }
        return answer;
    }
}