import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        
        for(String[] clothe: clothes) {
            String str = clothe[1];
            if(map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            }
            else {
                map.put(str, 1);
            }
            set.add(str);
        }
        
        int answer = 1;
        for(String str: set) {
            int count = map.get(str);
            answer *= (count + 1);
        }
        return answer - 1;
    }
}