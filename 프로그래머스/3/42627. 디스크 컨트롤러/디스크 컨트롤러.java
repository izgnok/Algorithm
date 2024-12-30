import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        
        PriorityQueue<Node> all = new PriorityQueue<>((o1, o2) -> o1.addTime - o2.addTime);
        
        int idx = 0;
        for(int[] job: jobs) {
            all.add(new Node(idx, job[1], job[0]));
            idx++;
        }
        
        PriorityQueue<Node> wait = new PriorityQueue<>((o1, o2) -> {
            if(o1.workTime == o2.workTime) {
                if(o1.addTime == o2.addTime) {
                    return o1.num - o2.num;
                }
                return o1.addTime - o2.addTime;
            }
            return o1.workTime - o2.workTime;
        });
        
        int sum = 0;
        int time = 0;
        while(!all.isEmpty() || !wait.isEmpty()) {
            
            // 대기큐 삽입
            while(true) {
                if(!all.isEmpty() && time >= all.peek().addTime) {
                    wait.add(all.poll());
                }
                else break;
            }
            
            // 대기큐에서 한개 꺼내서 작업
            if(wait.isEmpty()) {
                time = all.peek().addTime;
                continue;
            }
            Node node = wait.poll();
            time += node.workTime;
            sum += (time - node.addTime);
        }
        return sum / idx;
    }
    
    static class Node {
        int num, workTime, addTime;
        
        Node (int num, int workTime, int addTime) {
            this.num = num;
            this.workTime = workTime;
            this.addTime = addTime;
        }
    }
}