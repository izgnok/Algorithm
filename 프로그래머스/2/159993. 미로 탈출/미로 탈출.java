import java.util.*;

class Solution {
    
    static int N, M;
    static Node start;
    static char[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        map = new char[N][M];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S') start = new Node(i, j, 0, 0);
            }
        }
        
        int answer = bfs();
        return answer;
    }
    
    static public int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][][] visit = new boolean[N][M][2];
        q.add(start);
        visit[start.x][start.y][0] = true;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            
            for(int i=0; i<4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                
                if(row < 0 || row >= N || col < 0 || col >= M) continue;
                if(map[row][col] == 'X') continue;
                if(visit[row][col][node.open]) continue;
                if(map[row][col] == 'E' && node.open == 1) {
                    return node.count + 1;
                }
                
                visit[row][col][node.open] = true;
                if(map[row][col] == 'L') {
                    visit[row][col][1] = true;
                    q.add(new Node(row, col, 1, node.count + 1));
                }
                else {
                    q.add(new Node(row, col, node.open, node.count + 1));
                }
            }
        }
        return -1;
    }
    
    
    static class Node {
        int x, y, open, count;
        
        Node(int x, int y, int open, int count) {
            this.x = x;
            this.y = y;
            this.open = open;
            this.count = count;
        }
    }
}