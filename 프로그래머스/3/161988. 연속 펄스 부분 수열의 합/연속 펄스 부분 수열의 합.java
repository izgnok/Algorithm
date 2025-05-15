import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        
        int size = sequence.length;
        
        int[] arr1 = new int[size];
        int[] arr2 = new int[size];
        
        int k = 1;
        for(int i=0; i<size; i++) {
            arr1[i] = sequence[i] * k;
            arr2[i] = sequence[i] * k * -1;
            k *= -1;
        }
        
        
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        long sum1 = 0;
        long sum2 = 0;
        
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        for(int i=0; i<size; i++) {
            
            if(sum1 + arr1[i] <= 0) {
                max1 = Math.max(max1, arr1[i]);
                while(!q1.isEmpty()) {
                    sum1 -= q1.poll();
                }
            }
            else {
                sum1 += arr1[i];
                q1.add(arr1[i]);
            }
            
            if(sum2 + arr2[i] <= 0) {
                max2 = Math.max(max2, arr2[i]);
                while(!q2.isEmpty()) {
                    sum2 -= q2.poll();
                }
            }
            else {
                sum2 += arr2[i];
                q2.add(arr2[i]);
            }
            
            max1 = Math.max(sum1, max1);
            max2 = Math.max(sum2, max2);
        }
        
        return Math.max(max1, max2);
    }
}