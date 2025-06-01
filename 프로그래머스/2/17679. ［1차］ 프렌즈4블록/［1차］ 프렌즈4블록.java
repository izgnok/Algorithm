import java.util.*;

class Solution {

    static int answer = 0;
    public int solution(int n, int m, String[] board) {
        
        char[][] map;map = new char[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        
        while(true) {
            boolean end = remove(n, m, map);
            if(end) break;
            move(n, m, map);
        }
        return answer;
    }
    
    static boolean remove(int n, int m, char[][] map) {
        boolean[][] check = new boolean[n][m];
        boolean end = true;
        
        for(int i=0; i<n - 1; i++) {
            for(int j=0; j<m - 1; j++) {
                if(map[i][j] == '0') continue;
                if (map[i][j] == map[i+1][j] && map[i][j] == map[i][j+1] && map[i][j] == map[i+1][j+1]) {            
                    check[i][j] = true;
                    check[i+1][j] = true;
                    check[i][j+1] = true;
                    check[i+1][j+1] = true;
                    end = false;
                }
            }
        }
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(check[i][j]) {
                    map[i][j] = '0';
                    answer++;
                }
            }
        }
        
        return end;
    }
    
    static void move(int n, int m, char[][] map) {
        
        for(int j=0; j<m; j++) {
            for(int i=n-2; i>=0; i--) {
                int cur = i;
                int next = i+1;
                while(next < n && map[next][j] == '0') {
                    map[next][j] = map[cur][j];
                    map[cur][j] = '0';
                    cur++;
                    next++;
                }
            }
        }
    }
    
}