import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int[][] ratios = {
            {1,1}, {1,2}, {2,1}, {2,3}, {3,2}, {3,4}, {4,3}
        };


        for (int w : weights) {
            for (int[] r : ratios) {
                int a = w * r[1];
                if(a % r[0] != 0) continue;
                int b = a / r[0];
                
                if(!map.containsKey(b)) continue;
                answer += map.get(b);
            }

            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        return answer;
    }
}
