import java.util.*;

class Solution {
    
    static int[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Node start, target;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        map = new int[101][101];
        for(int[] rect: rectangle) {
            for(int i=rect[1] * 2; i <= rect[3] * 2; i++) {
                for(int j=rect[0] * 2; j<= rect[2] * 2; j++) {
                    if(map[i][j] == 2) continue;
                    if(i == rect[1] * 2|| i == rect[3] * 2 || j == rect[0] * 2 || j == rect[2] * 2) {
                        map[i][j] = 1;
                        continue;
                    }
                    map[i][j] = 2;
                }
            }
        }
        
        start = new Node(characterY * 2, characterX * 2, 0);
        target = new Node(itemY * 2, itemX * 2, 0);
        int answer = bfs();
        return answer;
    }
    
    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);
        boolean[][] visit = new boolean[101][101];
        visit[start.x][start.y] = true;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            for(int i=0; i<4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                
                if(row <= 0 || row > 100 || col <= 0 || col > 100) continue;
                if(map[row][col] != 1) continue;
                if(visit[row][col]) continue;
                
                if(row == target.x && col == target.y) {
                    return (node.count + 1) / 2;
                }
                
                visit[row][col] = true;
                q.add(new Node(row, col, node.count + 1));
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
        
        @Override
        public String toString() {
            return "Row: " + x + ", Col: " + y + ", Count: " + count;
        }
    }
}