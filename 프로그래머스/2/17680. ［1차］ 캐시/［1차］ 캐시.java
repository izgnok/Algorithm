import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        
        if(cacheSize == 0) return cities.length * 5;
        
        HashMap<String, Integer> map = new HashMap<>();
        int time = 0;
        for(String city: cities) {
            // 소문자 통일
            city = city.toLowerCase(); 
            
            // 캐시 히트
            if(map.containsKey(city)) {
                time++;
                map.put(city, time);
                continue;
            }
            
            // 캐시미스
            time += 5;
            // 캐시가 꽉 찬 경우
            if(map.size() >= cacheSize) {
                String removeCity = null;
                int min = Integer.MAX_VALUE;
                for(String str: map.keySet()) {
                    if(map.get(str) < min) {
                        min = map.get(str);
                        removeCity = str;
                    }
                }
                map.remove(removeCity);
            }
            map.put(city, time);
        }
        return time;
    }
}