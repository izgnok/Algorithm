import java.util.*;

class Solution {
    
    public int solution(String name) {
        int length = name.length();
        int answer = 0;
        int move = length - 1; // 기본적으로 끝까지 오른쪽으로 이동하는 경우

        // 알파벳 변경 비용 계산 및 커서 이동 최적화
        for (int i = 0; i < length; i++) {
            // 위, 아래 이동 최소값 계산
            int num = name.charAt(i) - 'A';
            answer += Math.min(num, 26 - num);

            // 연속된 'A' 구간 처리 (커서 이동 최적화)
            int next = i + 1;
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }

            // 커서 이동 최소값 갱신
            move = Math.min(move, i * 2 + length - next);  // 오른쪽 -> 왼쪽 이동 고려
            move = Math.min(move, (length - next) * 2 + i); // 왼쪽 -> 오른쪽 이동 고려
        }

        return answer + move; // 알파벳 변경 비용 + 최소 커서 이동 횟수
    }
}