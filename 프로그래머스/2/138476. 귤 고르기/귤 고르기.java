import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        
        List<Node> list = new ArrayList<>();
        Arrays.sort(tangerine);
        
        int size = tangerine.length;
        int pre = tangerine[0];
        int count = 1;
        for(int i=1; i<size; i++) {
            int cur = tangerine[i];
            if(pre == cur) {
                count++;
            }
            else {
                list.add(new Node(pre, count));
                pre = cur;
                count = 1;
            }
        }
        list.add(new Node(pre, count));
        
        Collections.sort(list, (o1, o2) -> o2.count - o1.count);
        int idx = 0;
        while(k > 0) {
            k -= list.get(idx).count;
            idx++;
        }
        return idx;
    }
    
    static class Node {
        int num, count;
        
        Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}