import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        Set<String> keys = new HashSet<>();

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        for (int i = 0; i < str1.length() - 1; i++) {
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i + 1);
            if (Character.isLetter(c1) && Character.isLetter(c2)) {
                String key = "" + c1 + c2;
                map1.put(key, map1.getOrDefault(key, 0) + 1);
                keys.add(key);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i + 1);
            if (Character.isLetter(c1) && Character.isLetter(c2)) {
                String key = "" + c1 + c2;
                map2.put(key, map2.getOrDefault(key, 0) + 1);
                keys.add(key);
            }
        }

        if (map1.isEmpty() && map2.isEmpty()) return 65536;

        int intersection = 0;
        int union = 0;

        for (String key : keys) {
            int count1 = map1.getOrDefault(key, 0);
            int count2 = map2.getOrDefault(key, 0);
            intersection += Math.min(count1, count2);
            union += Math.max(count1, count2);
        }

        double similarity = (double) intersection / union;
        return (int) (similarity * 65536);
    }
}
