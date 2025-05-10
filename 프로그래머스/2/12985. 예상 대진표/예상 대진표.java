class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        
        int count = 1;
        while((a + 1) / 2 != (b + 1) / 2) {
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            count++;
        }
        return count;
    }
}