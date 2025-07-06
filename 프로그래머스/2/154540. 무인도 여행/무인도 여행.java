import java.util.*;

class Solution {
    
    static int[][] map;
    static int[][] dir = {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
    static int N, M;
    static boolean[][] visit;    

    public List<Integer> solution(String[] maps) {
        
        N = maps.length;
        M = maps[0].length();
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(maps[i].charAt(j) == 'X') {
                    continue;
                }
                map[i][j] = maps[i].charAt(j) - '0';
            }
        }
        
        List<Integer> list = new ArrayList<>();
        visit = new boolean[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visit[i][j]) continue;
                if(map[i][j] == 0) continue;
                visit[i][j] = true;
                list.add(bfs(new Node(i, j)));
            }
        }
        
        if(list.isEmpty()) list.add(-1);
        else Collections.sort(list);
        return list;
    }
    
    
    public int bfs(Node start) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);
        int sum = 0;

        while(!q.isEmpty()) {
            Node node = q.poll();
            sum += map[node.x][node.y];
            
            for(int i=0; i<4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                
                if(row < 0 || row >= N || col < 0 || col >= M) continue;
                if(visit[row][col]) continue;
                if(map[row][col] == 0) continue;
                
                visit[row][col] = true;
                q.add(new Node(row, col));
            }
        }
        return sum;
    }
    
    public class Node {
        int x, y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}