import java.util.*;

class Solution {
    
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks); // 바위를 정렬
        
        int start = 1;             // 최소 거리
        int end = distance;        // 최대 거리
        int answer = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            
            if (simulation(mid, rocks, distance, n)) {
                answer = mid; // 가능한 거리 갱신
                start = mid + 1; // 더 큰 거리 탐색
            } else {
                end = mid - 1; // 더 작은 거리 탐색
            }
        }
        return answer;
    }
    
    // 최소 거리(mid)를 유지할 수 있는지 확인
    private boolean simulation(int mid, int[] rocks, int distance, int n) {
        int removed = 0; // 제거한 바위 수
        int prev = 0;    // 이전 바위 위치

        for (int rock : rocks) {
            if (rock - prev < mid) { // 거리가 mid보다 작으면 바위 제거
                removed++;
                if (removed > n) {  // 제거 제한 초과
                    return false;
                }
            } else { 
                prev = rock; // 바위 유지
            }
        }
        // 마지막 도착점과의 거리 검사
        if (distance - prev < mid) {
            removed++;
            if(removed > n) {
                return false;
            }
        }

        return true;
    }
}