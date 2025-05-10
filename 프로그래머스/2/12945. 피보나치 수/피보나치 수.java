class Solution {
    
    static int[] DP;
    
    public int solution(int n) {
        DP = new int[n + 1];
        return Fibonacci(n);
    }
    
    public int Fibonacci(int n) {
        
        if(n <= 1) {
            DP[n] = n;
            return n;
        }
        
        if(DP[n] != 0) {
            return DP[n];
        }
        
        DP[n] = (Fibonacci(n - 1) + Fibonacci(n - 2)) % 1234567;
        return DP[n];
    }
}