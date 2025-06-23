import java.util.*;

class Solution {
    
    static List<Map<String, Integer>> list;
    public List<String> solution(String[] orders, int[] courses) {
        
        list = new ArrayList<>();
        for(int i=0; i<=10; i++) {
            list.add(new HashMap<>());
        }
        
        for(String order: orders) {
            
            char[] tmp = order.toCharArray();
            Arrays.sort(tmp);
            StringBuilder sb = new StringBuilder();
            for(char c: tmp) {
                sb.append(c);
            }
            for(int course: courses) {
                dfs(sb.toString(), course, 0, new boolean[order.length()], 0);
            }
        }
        
                
        List<String> answer = new ArrayList<>();
        for(int course: courses) {
            Map<String, Integer> map = list.get(course);
            
            int max = 0;
            List<String> result = new ArrayList<>();
            for(String str: map.keySet()) {
                int count = map.get(str);
                if(count <= 1) continue;
                if(max < count) {
                    max = count;
                    result.clear();
                    result.add(str);
                }
                else if(max == count) {
                    result.add(str);
                }
            }
            
            for(String str: result) {
                answer.add(str);
            }
        }
        Collections.sort(answer);
        return answer;
    }
    
    static void dfs(String order, int course, int depth, boolean[] check, int count) {
        if(count == course) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<depth; i++) {
                if(check[i]) sb.append(order.charAt(i));
            }
            Map<String, Integer> map = list.get(course);    
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            return;
        }
        if(depth == order.length()) {
            return;
        }
        
        check[depth] = true;
        dfs(order, course, depth + 1, check, count + 1);
        check[depth] = false;
        dfs(order, course, depth + 1, check, count);
    }
}