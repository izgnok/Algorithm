import java.util.*;


class Solution {
    
    static List<String> airPlane; 
    static String[] result;
    static boolean[] visit;
    
    public String[] solution(String[][] tickets) {
        
        airPlane = new ArrayList<>();
        
        for(String[] ticket: tickets) {
            airPlane.add(ticket[0] + "-" + ticket[1]);
        }
        result = new String[airPlane.size() + 1];
        Arrays.fill(result, "ZZZ");
        visit = new boolean[airPlane.size()];
        dfs(0, "ICN", new String[airPlane.size() + 1]);
        return result;
    }
    
    static void dfs(int depth, String cur, String[] list) {
        
        if(depth == airPlane.size()) {
            list[depth] = cur;
            
            boolean check = false;
            for(int i=0; i<= depth; i++) {
                if(result[i].compareTo(list[i]) > 0) {                
                    check = true;
                    break;
                }
                else if(result[i].compareTo(list[i]) < 0) {
                    check = false;
                    break;
                }
            }
            if(check) {
                result = list.clone();
            }
            return;
        }
        
        
        for(int i=0; i<airPlane.size(); i++) {
            if(visit[i]) continue;
            
            String[] str = airPlane.get(i).split("-");
            String start = str[0];
            String end = str[1];
            
            if(!cur.equals(start)) continue;
            list[depth] = start;
            visit[i] = true;
            dfs(depth + 1, end, list);
            visit[i] = false;
        }
    }
}