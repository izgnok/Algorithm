class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int size = stations.length;
        int cover = w * 2 + 1;
        int start = 1;
        int pre = 0;
        for(int station: stations) {
            start = Math.max(station - w, start);
            int count = start - pre - 1;
            
            int tmp = count / cover;
            answer += tmp;

            if(tmp * cover < count) answer++;
            pre = Math.min(station + w, n);
        }

        
        if(pre < n) {
            int count = n - pre;
            int tmp = count / cover;
            answer += tmp;
            if(tmp * cover < count) answer++;
        }
        
        return answer;
    }
}