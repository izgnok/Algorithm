import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        Queue<Node> q = new ArrayDeque<>();
        
        int size = priorities.length;
        for(int i=0; i<size ;i++) {
            int num = priorities[i];
            pq.add(num);
            q.add(new Node(num, i));
        }
        
        int answer = 0;
        int count = 0;
        while(!q.isEmpty()) {
            Node node = q.poll();
            
            if(pq.peek() > node.num) {
                q.add(node);
                continue;
            }
            
            count++;
            if(node.index == location) {
                answer = count;
                break;
            }
            pq.poll();
        }
        return answer;
    }
    
    public class Node {
        int num, index;
        
        Node(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }
}