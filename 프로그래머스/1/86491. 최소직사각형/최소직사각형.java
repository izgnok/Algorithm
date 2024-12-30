import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        
        int row = 0;
        int col = 0;
        
        for(int[] size : sizes) {
            int cur = Math.min(size[0], size[1]);
            row = Math.max(row, cur);
            
            cur = Math.max(size[0], size[1]);
            col = Math.max(col, cur);
        }
        
        return row * col;
    }
}