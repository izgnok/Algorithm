import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        int row = 0;
        int col = 0;

        Set<Node> set = new HashSet<>();
        for (char dir : dirs.toCharArray()) {
            int nr = row;
            int nc = col;
            if (dir == 'U') nr++;
            if (dir == 'D') nr--;
            if (dir == 'L') nc--;
            if (dir == 'R') nc++;


            if (nr < -5 || nr > 5 || nc < -5 || nc > 5) continue;
            Node node = new Node(row, col, nr, nc);
            Node node2 = new Node(nr, nc, row, col);
            row = nr;
            col = nc;

            if (set.contains(node) || set.contains(node2)) continue;
            set.add(node);
            set.add(node2);
            answer++;
        }
        return answer;
    }

    static class Node {
        int x1, y1, x2, y2;

        public Node(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node obj = (Node) o;
            return x1 == obj.x1 && y1 == obj.y1 && x2 == obj.x2 && y2 == obj.y2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x1, y1, x2, y2);
        }
        
        @Override
        public String toString() {
            return "(" + x1 + "," + y1 + ") -> (" + x2 + "," + y2 + ")";
        }
    }
}
