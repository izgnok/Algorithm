import java.util.*;

class Solution {
    static boolean[] check;
    static boolean[] isPrime;
    static int size;
    static int answer;
    static String number;
    static int max = 9999999;
    static HashSet<Integer> set;
    
    public int solution(String numbers) {
        number = numbers;
        size = numbers.length();
        check = new boolean[size];
        isPrime = new boolean[10000000];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        
        for(int i=2; i< Math.sqrt(max); i++) {
            if(isPrime[i]) {
                for(int j= i*i; j<= max; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
        
        answer = 0;
        set = new HashSet<>();
        for(int i=1; i<=size; i++) {
            dfs(0, "", i);
        }
        return answer;
    }
    
    static void dfs(int depth, String cur, int r) {
        if(depth == r) {
            if(cur.equals("")) return;
            
            // 소수판정
            int num = Integer.parseInt(cur);
            System.out.println(num);
            if(isPrime[num] && !set.contains(num)) {
                set.add(num);
                answer++;
            }
            return;
        }
        
        for(int i=0; i<size; i++) {
            if(check[i]) continue;
            check[i] = true;
            dfs(depth + 1, cur + number.charAt(i), r);
            check[i] = false;
        }
    }
}