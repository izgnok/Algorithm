import java.util.*;

class Solution {
    static int[][] map;
    static int N, M;
    static int answer;
    static Node start;
    
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(String[] board) {
        answer = -1;
        N = board.length;
        M = board[0].length();
        map = new int[N][M];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                char c = board[i].charAt(j);
                if(c == 'D') {
                    map[i][j] = -1;
                }
                else if(c == 'R') {
                    start = new Node(i,j,0);
                } else if(c == 'G') {
                    map[i][j] = 1;
                }
            }
        }
        
        bfs();
        return answer;
    }
    
    static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][M];
        q.add(start);
        visit[start.x][start.y] = true;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            if(map[node.x][node.y] == 1) {
                answer = node.count;
                return;
            }
            
            for(int i=0; i<4; i++) {
                int row = node.x;
                int col = node.y;
                while(true) {
                    row += dir[i][0];
                    col += dir[i][1];
                    
                    if(row < 0 || row >= N || col < 0 || col >= M) {
                        break;
                    }
                    if(map[row][col] == -1) {
                        break;
                    }
                }
                row -= dir[i][0];
                col -= dir[i][1];
                
                if(visit[row][col]) continue;
                visit[row][col] = true;
                q.add(new Node(row, col, node.count + 1));
            }
        }
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