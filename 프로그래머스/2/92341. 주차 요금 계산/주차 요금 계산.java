import java.util.*;

class Solution {
    
    static Map<Integer, Node> map;
    static Map<Integer, Integer> result;
    
    public int[] solution(int[] fees, String[] records) {
        
        map = new HashMap<>();
        result = new HashMap<>();
        
        for(String str: records) {
            String[] info = str.split(" ");
            String[] time = info[0].split(":");
            int num = Integer.parseInt(info[1]);
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);

            if(info[2].equals("IN")) {
                map.put(num, new Node(hour, minute));
            }
            else {
                Node node = map.get(num);
                calc(num, node, hour, minute);
            }
        }
        // 출차안된 차량 처리
        for(int num: new ArrayList<>(map.keySet())) {
            Node node = map.get(num);
            calc(num, node, 23, 59);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num: result.keySet()) {
            pq.add(num);
        }
        
        int[] answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) {
            int num = pq.poll();
            int total = result.get(num);
            int sum = fees[1];
            if (total > fees[0]) {
                total -= fees[0];
                int count = total / fees[2] + 1;
                if(total % fees[2] == 0) count--;
                sum += count * fees[3];
            }
            answer[idx++] = sum;
        }
        return answer;
    }
    
    public void calc(int num, Node node, int hour, int minute) {
        int total = (hour - node.hour) * 60;
        total += minute - node.minute;
        
        result.put(num, result.getOrDefault(num, 0) + total);
        map.remove(num);
    }

    static class Node {
        int hour, minute;
        
        Node(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }
    }
}