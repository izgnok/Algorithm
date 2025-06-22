import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        List<Node> list = new ArrayList<>();
        
        for(String[] time: book_time) {
            String[] start = time[0].split(":");
            
            int startHour = Integer.parseInt(start[0]) * 100;
            int startMinute = Integer.parseInt(start[1]);
            
            String[] end = time[1].split(":");
            int endHour = Integer.parseInt(end[0]) * 100;
            int endMinute = Integer.parseInt(end[1]) + 10;
            if(endMinute >= 60) {
                endHour += 100;
                endMinute -= 60;
            }
            
            list.add(new Node(startHour + startMinute, endHour + endMinute));
        }
        
        Collections.sort(list, (o1, o2) -> {
            if(o1.start == o2.start) return o1.end - o2.end;
            return o1.start - o2.start;
        });
        
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Node node: list) {
            if(pq.isEmpty()) {
                pq.add(node.end);
                continue;
            }
            
            if(pq.peek() <= node.start) {
                pq.poll();   
            }
            pq.add(node.end);
        }
        
        return pq.size();
    }
    
    static class Node {
        int start, end;
        
        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}