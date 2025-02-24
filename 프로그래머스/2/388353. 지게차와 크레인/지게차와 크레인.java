import java.util.*;

class Solution {

    static int N, M;
    static char[][] map;
    static int answer;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(String[] storage, String[] requests) {
        
        N = storage.length;
        M = storage[0].length();
        
        map = new char[N + 2][M + 2];
        for(int i=0; i< N+2; i++) {
            Arrays.fill(map[i], '0');
        }
        
        for(int i=1; i<=N; i++) {
            String str = storage[i-1];
            for(int j=1; j<=M; j++) {
                map[i][j] = str.charAt(j - 1);
            }
        }
        
        answer = 0;
        for(String req: requests) {
            if(req.length() == 1) {
                zig(req.charAt(0));
            } else {
                zip(req.charAt(0));
            }
        }
        return N * M - answer;
    }
    
    static void zig(char req) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0));
        Queue<Node> removeList = new ArrayDeque<>();
        
        boolean[][] visit = new boolean[N + 2][M + 2];
        visit[0][0] = true;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            if(map[node.x][node.y] == req) {
                removeList.add(new Node(node.x, node.y));
                continue;
            }
            
            for(int i=0; i<4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                
                if(row < 0 || row >= N + 2 || col < 0 || col >= M + 2) continue;
                if(visit[row][col]) continue;
                if(map[row][col] != '0' && map[row][col] != req) continue;
                
                q.add(new Node(row, col));
                visit[row][col] = true;
            }
        }
        
        while(!removeList.isEmpty()) {
            answer++;
            Node node = removeList.poll();
            map[node.x][node.y] = '0';
        }
    }
    
    static void zip(char req) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0));
        Queue<Node> removeList = new ArrayDeque<>();
        
        boolean[][] visit = new boolean[N + 2][M + 2];
        visit[0][0] = true;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            if(map[node.x][node.y] == req) removeList.add(new Node(node.x, node.y));
            
            for(int i=0; i<4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                
                if(row < 0 || row >= N + 2 || col < 0 || col >= M + 2) continue;
                if(visit[row][col]) continue;
                
                q.add(new Node(row, col));
                visit[row][col] = true;
            }
        }
        while(!removeList.isEmpty()) {
            answer++;
            Node node = removeList.poll();
            map[node.x][node.y] = '0';
        }
    }
    
    static class Node {
        int x, y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
    }
}