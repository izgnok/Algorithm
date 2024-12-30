import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        
        Queue<Integer> wait = new ArrayDeque<>();
        for(int truck: truck_weights) {
            wait.add(truck);
        }
        
        Queue<Node> board = new ArrayDeque<>();
        int cur_weight = 0;
        int cur_length = 0;
        
        while(!wait.isEmpty() || !board.isEmpty()) {
            time++;
            
            // 내릴거있는지 체크
            if(!board.isEmpty()) {
                Node node = board.peek();
                if(time - node.time >= bridge_length) {
                    cur_weight -= node.weight;
                    cur_length--;
                    board.poll();
                }
            }
            // 올라갈수있는지 체크
            if(wait.isEmpty()) continue;
            if(cur_weight + wait.peek() <= weight && cur_length < bridge_length) {
                cur_weight += wait.peek();
                cur_length++;
                board.add(new Node(wait.poll(), time));
            }
        }
        return time;
    }
    
    static class Node {
        int weight, time;
        
        Node(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }
}