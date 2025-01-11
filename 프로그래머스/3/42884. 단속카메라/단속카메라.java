import java.util.*;

class Solution {
    
    public int solution(int[][] routes) {
        List<Node> list = new ArrayList<>();
        
        for(int[] route: routes) {
            list.add(new Node(route[0], route[1]));
        }
        Collections.sort(list, (o1, o2) -> {
            if(o1.end == o2.end) {
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        });
        
        int cur = list.get(0).end;
        int answer = 1;
        for(Node node: list) {
            if(cur < node.start) {
                cur = node.end;
                answer++;
                continue;
            }
            cur = Math.min(cur, node.end);
        }
        return answer;
    }
    
    static class Node{
        int start, end;
        
        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}