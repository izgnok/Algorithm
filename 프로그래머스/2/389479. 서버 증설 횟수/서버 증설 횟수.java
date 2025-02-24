import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        
        int answer = 0;
        int cur = 0;
        Queue<Node> q = new ArrayDeque<>();
        
        for(int i=0; i<24; i++) {
            if(!q.isEmpty()) {
                Node node = q.peek();
                if(node.time == i) {
                    cur -= node.count;
                    q.poll();
                }
            }
            
            if(cur > players[i] / m) continue;
            
            int next = players[i] / m;
            answer += next - cur;
            q.add(new Node(i + k, next - cur));
            cur = next;
        }
        return answer;
    }
    
    static class Node {
        int time, count;
        
        Node(int time, int count) {
            this.time = time;
            this.count = count;
        }
    }
}