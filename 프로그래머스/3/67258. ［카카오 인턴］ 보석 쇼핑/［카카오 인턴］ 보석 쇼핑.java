import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
              
        HashSet<String> total = new HashSet<>();
        Queue<Node> q = new ArrayDeque<>();
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
    
        for(String gem: gems) {
            total.add(gem);
            map.put(gem, 0);
        }
        int size = gems.length;
        int start = 0;
        int end = 0;
        
        int min = Integer.MAX_VALUE;
        for(int i=0; i<size; i++) {
            
            String gem = gems[i];
            q.add(new Node(i, gem));
            map.put(gem, map.get(gem) + 1);
            set.add(gem);
            
            if(set.size() == total.size()) {
                while(!q.isEmpty()) {
                    Node node = q.peek();
                    if(map.get(node.gem) == 1) {
                        if(min > q.size()) {
                            min = q.size();
                            start = node.idx + 1;
                            end = i + 1;
                        }
                        break;
                    }
                    else {
                        map.put(node.gem, map.get(node.gem) - 1);
                    }
                    q.poll();
                }
            }
        }
        
        return new int[] {start, end};
    }
    
    static class Node{
        int idx;
        String gem;
        
        Node(int idx, String gem) {
            this.idx = idx;
            this.gem = gem;
        }
    }
}