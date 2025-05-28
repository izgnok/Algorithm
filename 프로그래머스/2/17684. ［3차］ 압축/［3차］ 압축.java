import java.util.*;

class Solution {
    public List<Integer> solution(String msg) {
        
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            char c = (char) ('A' + i);
            map.put(String.valueOf(c), i + 1);
        }
        
        List<Integer> list = new ArrayList<>();
        int idx = 27;
        int i = 0;
        String str = "";
        while(i < msg.length()) {
            if(map.containsKey(str + msg.charAt(i))) {
                str += msg.charAt(i);
            }
            else {
                list.add(map.get(str));
                map.put(str + msg.charAt(i), idx++);
                str = "" + msg.charAt(i);
            }
            i++;
        }
        list.add(map.get(str));
        return list;
    }
}