import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        
        
        int N = want.length;
        int size = discount.length;
        int answer = 0;
        for(int i=0; i<size - 9; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            for(int j=i; j<i+10; j++) {
                if(!map.containsKey(discount[j])) {
                    map.put(discount[j], 0);
                }
                map.put(discount[j], map.get(discount[j]) + 1);
            }
            
            boolean check = true;
            for(int j=0; j<N; j++) {
                if(!map.containsKey(want[j])) {
                    check = false;
                    break;
                }

                if(map.get(want[j]) != number[j]) {
                    check = false;
                    break;
                }
            }
            if(check) {
                answer++;
            }
        }
        return answer;
    }
}