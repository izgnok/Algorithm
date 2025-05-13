import java.util.*;

class Solution {
    public int[] solution(int n, int s) {

        if (n > s) {
            return new int[]{-1};
        }

        int[] result = new int[n];
        int idx = 0;
        while (n > 0) {
            int num = s / n;
            s -= num;
            n--;
            result[idx++] = num;
        }

        return result;
    }
}
