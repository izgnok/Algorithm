import java.util.*;

class Solution {
    public int solution(String skills, String[] skill_trees) {
        
        HashMap<Character, Character> map = new HashMap<>();
        for(int i=skills.length() - 1; i>0; i--) {
            map.put(skills.charAt(i), skills.charAt(i-1));
        }
        
        int answer = 0;
        for(String skill_tree : skill_trees) {
            HashSet<Character> set = new HashSet<>();
            
            boolean check = true;
            for(char skill: skill_tree.toCharArray()) {
                if(map.containsKey(skill) && !set.contains(map.get(skill))) {
                    check = false;
                    break;
                }
                set.add(skill);
            }
            if(check) answer++;
        }
        return answer;
    }
}