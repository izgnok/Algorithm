import java.util.*;

class Solution {
    static int maxSheep = 0;
    static List<List<Integer>> tree;
    static int[] nodeInfo;

    public int solution(int[] info, int[][] edges) {
        nodeInfo = info;
        int n = info.length;
        tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
        }

        // DFS 탐색 시작 (초기 상태: 루트 노드, 양 1마리, 늑대 0마리)
        List<Integer> path = new ArrayList<>();
        path.add(0); // 루트 노드 추가
        dfs(0, 0, 0, path);

        return maxSheep;
    }

    static void dfs(int sheep, int wolf, int current, List<Integer> path) {
        // 현재 노드의 상태 업데이트
        if (nodeInfo[current] == 0) sheep++; // 양
        else wolf++; // 늑대

        // 조건: 늑대가 양의 수 이상이 되면 탐색 종료
        if (wolf >= sheep) return;

        // 최대 양의 수 갱신
        maxSheep = Math.max(maxSheep, sheep);

        // 다음 탐색 가능한 노드 추가
        List<Integer> nextNodes = new ArrayList<>(path);
        nextNodes.remove(Integer.valueOf(current)); // 현재 노드 제거
        nextNodes.addAll(tree.get(current)); // 자식 노드 추가

        // 가능한 모든 경로로 이동
        for (int next : nextNodes) {
            dfs(sheep, wolf, next, nextNodes);
        }
    }
}