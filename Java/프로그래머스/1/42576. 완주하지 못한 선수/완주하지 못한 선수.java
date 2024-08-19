import java.util.*;
import java.io.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();

        for (String s : participant) {
            if (map.containsKey(s)) {
                map.replace(s, map.get(s) + 1);
            }
            else {
                map.put(s, 1);
            }
        }
        for(String s : completion) {
            if(map.containsKey(s)) {
                map.replace(s, map.get(s) - 1);
                if(map.get(s) == 0) {
                    map.remove(s);
                }
            }
        }

        Set<String> keySet = map.keySet();
        for(String s : keySet) {
            answer = s;
        }
        return answer;
    }
}