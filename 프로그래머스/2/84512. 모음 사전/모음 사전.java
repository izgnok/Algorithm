import java.util.*;

class Solution {
    static HashMap<Integer, Integer> map = new HashMap<>();
    static int idx;
    
    public int solution(String word) {
        
        idx = 0;
        dfs(0, "");
  
        String numStr = "";
        for(int i=0; i< word.length(); i++) {
            char c = word.charAt(i);
            if(c == 'A') numStr += 1;
            else if(c == 'E') numStr += 2;
            else if(c == 'I') numStr += 3;
            else if(c == 'O') numStr += 4;
            else if(c == 'U') numStr += 5;
        }
        int num = Integer.parseInt(numStr);
        int answer = map.get(num);
        return answer;
    }
    
    static void dfs(int depth, String str) {
        if(!str.equals("")) {
            map.put(Integer.parseInt(str), ++idx);
        }
        
        if(depth == 5) {
            return;
        }
        
        for(int i=1; i<=5; i++) {
            dfs(depth + 1, str + i);
        }
    }
}