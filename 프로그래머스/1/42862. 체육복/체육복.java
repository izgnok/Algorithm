import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] count = new int[n + 2];
        Arrays.fill(count, 1);
        count[0] = 0;
        count[n + 1] = 0;
        
        for(int num: lost) {
            count[num]--;
        }
        for(int num: reserve) {
            count[num]++;
        }
        
        for(int i=1; i<= n; i++) {
            if(count[i] > 0) continue;
            
            boolean check = false;
            if(count[i-1] == 2) {
                count[i-1]--;
                count[i]++;
                check = true;
            }
            if(!check) {
                if(count[i+1] == 2) {
                    count[i+1]--;
                    count[i]++;
                }
            }
        }
        
        int answer = 0;
        for(int i=1; i<=n; i++) {
            if(count[i] >= 1) answer++;
        }
        return answer;
    }
}