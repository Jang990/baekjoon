class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;

        int now = n;
        while(now/a != 0) {
        	int gains = (now/a)*b;
        	now = gains+now%a;
        	answer += gains;
        }
        
        return answer;
    }
}