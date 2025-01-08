import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        int N = people.length;
        Arrays.sort(people);
        boolean[] visit = new boolean[N];
        for(int i=0; i < N; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            answer++;
            
            if(i == N - 1) continue;
            int start = i + 1;
            int end = N - 1;
            int idx = 0;
            while(start <= end) {
                int mid = (start + end) / 2;
                
                if(!visit[mid] && people[i] + people[mid] <= limit) {
                    idx = mid;
                    start = mid + 1;
                }
                else {
                    end = mid - 1;
                }
            }
            if(idx > i) visit[idx] = true;
        }
        return answer;
    }
}