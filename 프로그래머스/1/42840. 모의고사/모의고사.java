import java.util.*;

class Solution {
    public List<Integer> solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        int max = 0;
        
        int[][] patterns = {
            {1, 2, 3, 4, 5},           // first
            {2, 1, 2, 3, 2, 4, 2, 5},  // second
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5} // third
        };

        for(int i=0; i<3; i++) {
            int count = simulation(patterns[i], answers);
            if(max < count) {
                max = count;
                if(!answer.isEmpty()) answer.clear();
                answer.add(i + 1);
            }
            else if(max == count) {
                answer.add(i + 1);
            }
        }
        
        return answer;
    }
    
    public int simulation(int[] arr, int[] answers) {
        int idx = 0;
        int size = arr.length;
        int count = 0;
        for(int answer: answers) {
            if(arr[idx++] == answer) count++;
            if(idx == size) idx = 0;
        }
        
        return count;
    }
}