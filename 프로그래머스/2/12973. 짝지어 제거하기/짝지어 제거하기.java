import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = -1;
        Deque<Character> dq = new ArrayDeque<>();
        dq.addLast(s.charAt(0));
        
        int size = s.length();
        for(int i=1; i<size; i++) {
            char c = s.charAt(i);
            
            if(!dq.isEmpty() && dq.peekLast() == c){
                dq.pollLast();
                continue;
            }
            dq.addLast(c);
        }
        
        if(dq.isEmpty()) answer = 1;
        else answer = 0;
        return answer;
    }
}