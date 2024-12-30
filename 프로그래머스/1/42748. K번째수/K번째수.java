import java.util.*;

class Solution {
    public List<Integer> solution(int[] array, int[][] commands) {
        
        List<Integer> answer = new ArrayList<>();
        for(int[] command: commands) {
            List<Integer> list = new ArrayList<>();
            
            int i = command[0] -1;
            int j = command[1];
            
            while(i < j) {
                list.add(array[i]);
                i++;
            }
            
            Collections.sort(list);
            answer.add(list.get(command[2] - 1));
        }
    
        return answer;
    }
}