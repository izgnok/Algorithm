import java.util.*;

class Solution {
    static int N, M;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(int[][] maps) {
        
        N = maps.length;
        M = maps[0].length;
        
        int answer = bfs(maps);
        return answer;
    }
    
    static int bfs(int[][] maps) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][M];
        q.add(new Node(0, 0, 1));
        visit[0][0] = true;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
         
            for(int i=0; i<4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                
                if(row < 0 || row >= N || col < 0 || col >= M) continue;
                if(maps[row][col] == 0) continue;
                if(visit[row][col]) continue;
                
                if(row == N -1 && col == M -1) {
                    return node.count + 1;
                }
                
                q.add(new Node(row, col, node.count + 1));
                visit[row][col] = true;
            }
        }
        return -1;
    }
    
    static class Node {
        int x, y, count;
        
        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}