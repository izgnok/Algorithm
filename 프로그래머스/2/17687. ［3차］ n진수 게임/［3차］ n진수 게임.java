import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        
        StringBuilder sb = new StringBuilder();
        
        int num = 0;
        while(sb.toString().length() < t * m) {
            String str = Integer.toString(num++, n).toUpperCase();
            sb.append(str);
        }
        String str = sb.toString();
        String answer = "";
        for(int i=p-1; i<t*m;  i+=m) {
            answer += str.charAt(i);
        }
        return answer;
    }
}