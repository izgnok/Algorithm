class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        // i: 가로,  j: 세로,  i >= j, 
        for(int i=1; i <= yellow; i++) {
            
            if(yellow % i != 0) continue;
            
            int j = yellow / i;
            if(i < j) continue;
            
            if(brown == i * 2 + j * 2 + 4) {
                answer[0] = i + 2;
                answer[1] = j + 2;
            }
        }
        return answer;
    }
}