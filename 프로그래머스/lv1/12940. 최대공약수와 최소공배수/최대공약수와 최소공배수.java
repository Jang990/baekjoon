class Solution {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        
        int n1 = Math.min(n, m), n2 = Math.max(n, m);
        answer[0] = 1;
        for (int i = 2; i <= n1; i++) {
			if(n1 % i == 0 && n2 % i == 0)
				answer[0] = i;
		}
        
        answer[1] = answer[0] * (n/answer[0]) * (m/answer[0]);
        
        return answer;
    }
}