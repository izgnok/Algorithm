import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        // 1. int 배열을 문자열 배열로 변환
        String[] numStrs = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numStrs[i] = String.valueOf(numbers[i]);
        }

        // 2. 정렬 기준 설정 (두 문자열을 연결한 값을 비교)
        Arrays.sort(numStrs, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        
        // 3. 0만 있는 경우 처리
        if (numStrs[0].equals("0")) { // 가장 큰 수가 0이면 결과는 0
            return "0";
        }

        // 4. 문자열 이어붙이기
        String answer = "";
        for (String num : numStrs) {
            answer += num;
        }

        return answer.toString();
    }
}