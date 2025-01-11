import java.util.*;

class Solution {
    public int solution(int N, int number) {
        int answer = -1;
        List<HashSet<Integer>> dp = new ArrayList<>();
        
        for(int i=0; i<9; i++) {
            dp.add(new HashSet<>());
        }
        
        for(int i=1; i<=8; i++) {
            dp.get(i).add(repeat(N, i));
            
            for(int j=1; j<i; j++) {
                for(int x: dp.get(j)) {
                    for(int y: dp.get(i - j)) {
                        dp.get(i).add(x + y);
                        dp.get(i).add(x - y);
                        dp.get(i).add(x * y);
                        if(y != 0) dp.get(i).add(x / y);
                    }
                }
            }
            
            if(dp.get(i).contains(number)) {
                answer = i;
                break;
            }
        }
        return answer;
    }
    
    public int repeat(int num, int n) {
        int result = 0;
        for(int i=0; i< n; i++) {
            result += num * Math.pow(10, i);
        }
        return result;
    }
}