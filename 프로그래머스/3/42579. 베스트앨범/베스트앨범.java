import java.util.*;

class Solution {
    public List<Integer> solution(String[] genres, int[] plays) {
        
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, List<Node>> map2 = new HashMap<>();
        
        int size = genres.length;
        for (int i = 0; i < size; i++) {
            String genre = genres[i];
            int count = plays[i];
            // map에 장르별 총 재생 수 누적
            map.put(genre, map.getOrDefault(genre, 0) + count);
            // map2에 장르별 노드 리스트 초기화 및 추가
            map2.putIfAbsent(genre, new ArrayList<>());
            map2.get(genre).add(new Node(i, count));
        }
        
        // 총 재생 수 기준으로 장르 정렬
        List<Node> list = new ArrayList<>();
        for (String genre : map.keySet()) {
            list.add(new Node(genre, map.get(genre)));
        }
        Collections.sort(list, (o1, o2) -> o2.count - o1.count);
        
        
        List<Integer> answer = new ArrayList<>();
        for(Node node: list) {
            List<Node> sorted = map2.get(node.genre);
            Collections.sort(sorted, (o1, o2) -> {
                if(o1.count == o2.count) {
                   return o1.num - o2.num;
                } 
                return o2.count - o1.count;
            });
            
            // 최대 2개의 노래 선택
            for (int i = 0; i < Math.min(2, sorted.size()); i++) {
                answer.add(sorted.get(i).num);
            }
        } 
        return answer;
    }
    
    static class Node {
        int num, count;
        String genre;
        
        Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
        
        Node(String genre, int count) {
            this.genre = genre;
            this.count = count;
        }
    }
}