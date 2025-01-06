import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            
            // 스택의 마지막 값이 현재 값보다 작으면 제거
            while (!stack.isEmpty() && k > 0 && stack.peekLast() < c) {
                stack.pollLast(); // 마지막 값 제거
                k--;
            }
            
            stack.addLast(c); // 현재 값을 스택에 추가
        }
        
        // k가 남아 있는 경우 뒤에서부터 제거
        while (k > 0) {
            stack.pollLast();
            k--;
        }
        
        // 결과 문자열 생성
        StringBuilder answer = new StringBuilder();
        for (char c : stack) {
            answer.append(c);
        }
        
        return answer.toString();
    }
}