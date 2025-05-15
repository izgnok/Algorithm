import java.util.*;

class Solution {
    
    static int answer;
    
    static boolean[] visit;
    static HashSet<String> set;
    static int N, M;
    
    static List<List<Integer>> list;
    public int solution(String[] user_id, String[] banned_id) {
    
        N = banned_id.length;
        M = user_id.length;
        list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
        }
         
        for(int i=0; i<N; i++) {
            String banId = banned_id[i];
            List<Integer> filter = new ArrayList<>();
            for(int j=0; j<banId.length(); j++) {
                if(banId.charAt(j) == '*') filter.add(i);
            }
            
            int M = user_id.length;
            
            for(int j=0; j<M; j++) {
                String user = user_id[j];
                if(user.length() != banId.length()) continue;
                
                boolean check = true;
                for (int k = 0; k < banId.length(); k++) {
                    if (banId.charAt(k) == '*') continue;
                    if (banId.charAt(k) != user.charAt(k)) {
                        check = false;
                        break;
                    }
                }
                if (check) list.get(i).add(j);
            }
        }
        answer = 0;
        
        visit = new boolean[M];
        set = new HashSet<>();
        set.add("");
        dfs(0);
        return answer;
    }
    
    
    static void dfs(int depth) {
        if(depth == N) {
            String str = "";
            for(int i=0; i<M; i++) {
                if(visit[i]) str += i;
            }
            if(!set.contains(str)) {
                set.add(str);
                answer++;
            }
            return;
        }
        
        List<Integer> nums = list.get(depth);
        
        for(int num: list.get(depth)) {
            if(visit[num]) continue;
            visit[num] = true;
            dfs(depth + 1);
            visit[num] = false;
        }
    }
}