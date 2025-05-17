import java.util.*;

class Solution {
    
    public int solution(int[][] board) {
        return bfs(board);
    }
    
    static int bfs(int[][] board) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });
        int N = board.length;
        boolean[][][] visit = new boolean[N][N][2];
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        pq.add(new Node(0, 0, 0, -1));
        
        int result = 987654321;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visit[node.x][node.y][node.dir / 2]) continue;
            visit[node.x][node.y][node.dir / 2] = true;
            if(node.x == N - 1 && node.y == N - 1) result = Math.min(result, node.cost);
            
            for(int i=0; i<4; i++) {
                int row = node.x + dir[i][0];
                int col = node.y + dir[i][1];
                
                if(row < 0 || row >= N || col < 0 || col >= N) continue;
                if(board[row][col] == 1) continue;
                if(visit[row][col][i / 2]) continue;
                
                int nextCost = node.cost + 100;
                if(node.dir != -1 && node.dir < 2 && i >= 2) nextCost += 500;
                if(node.dir != -1 && node.dir >= 2 && i < 2) nextCost += 500;
                pq.add(new Node(row, col, nextCost, i));
            }
        }
        return result;
    }

    static class Node {
        int x, y, cost, dir;
        
        Node(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
        
        @Override
        public String toString() {
            return "X: " + this.x + ", Y:" + this.y + ", cost: " + this.cost 
                + ", dir: " + this.dir;
        }
    }
}