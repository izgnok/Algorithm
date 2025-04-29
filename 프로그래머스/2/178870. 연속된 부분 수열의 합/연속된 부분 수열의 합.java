import java.util.*;

// class Solution {
//     public int[] solution(int[] sequence, int k) {
        
//         int size = sequence.length;       
        
//         int min = Integer.MAX_VALUE;
//         Deque<Node> dq = new ArrayDeque<>();
//         int cur = 0;
//         int start = 0;
//         int end = 0;
        
//         for(int i=0; i<size; i++) {
            
//             int num = sequence[i];
//             cur += num;
//             dq.addLast(new Node(i, num));
            
//             if(cur > k) {
//                 while(!dq.isEmpty() && cur > k) {
//                     cur -= dq.pollFirst().num;
//                 }
//             }
            
//             if(cur == k && min > dq.size()) {
//                 min = dq.size();
//                 start = dq.peekFirst().idx;
//                 end = dq.peekLast().idx;
//             }
//         }
        
//         return new int[] {start, end};
//     }
    
//     static class Node {
//         int idx, num; 
        
//         Node(int idx, int num) {
//             this.idx = idx;
//             this.num = num;
//         }
        
//         @Override
//         public String toString() {
//             return "IDX: " + this.idx + ", NUM: " + this.num;
//         }
//     }
// }

class Solution {
    public int[] solution(int[] sequence, int k) {
        int size = sequence.length;
        
        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int left = 0;
        int right = 0;
        int sum = 0;
        
        while(right < size) {
            sum += sequence[right++];
            
            while(sum > k) {
                sum -= sequence[left++];
            }
            
            if(sum == k && min > right - left) {
                min = right - left;
                start = left;
                end = right-1;
            }
        }
        
        return new int[] {start, end};
    }
}