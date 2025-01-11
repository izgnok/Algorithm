import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        HashSet<String> set = new HashSet<>();
        Arrays.sort(phone_book, (o1, o2) -> o1.length() - o2.length());
        
        for(String phone: phone_book) {
            if(!answer) break;
            String str = "";
            for(int i=0; i<phone.length(); i++) {
                str += phone.charAt(i);
                if(set.contains(str)) {
                    answer = false;
                    break;
                }
            }
            set.add(phone);
        }
        return answer;
    }
}