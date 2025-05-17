import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int N = s.length();
        int answer = 1;
        for(int i=1; i<N - 1; i++) {
            int front = i - 1;
            int back = i + 1;
            int count = 1;
            while(front >= 0 && back < N) {
                if(s.charAt(front) != s.charAt(back)) break;
                front--;
                back++;
                count += 2;
            }
            answer = Math.max(answer, count);
        }
        
        for (int i = 0; i < N - 1; i++) {
            int front = i;
            int back = i + 1;
            int count = 0;
            while (front >= 0 && back < N && s.charAt(front) == s.charAt(back)) {
                front--;
                back++;
                count += 2;
            }
            answer = Math.max(answer, count);
        }
        return answer;
    }
}