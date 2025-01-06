import java.util.*;

class Solution {
    static int N;
    static int answer;
    
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        
        answer = 0;
        dfs(0, target, numbers, 0);
        return answer;
    }
    
    static void dfs(int depth, int target, int[] numbers, int cur) {
        if(depth == N) {
            if(cur == target) answer++;
            return;
        }
        
        
        //더하기
        dfs(depth + 1, target, numbers, cur + numbers[depth]);
        
        // 빼기
        dfs(depth + 1, target, numbers, cur - numbers[depth]);

    }
}