import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        int answer = nums.length/2;
        
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }
        if(set.size() <= nums.length/2) answer = set.size();
        
        return answer;
    }
    
    
}