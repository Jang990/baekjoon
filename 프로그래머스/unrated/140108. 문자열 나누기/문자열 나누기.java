class Solution {
    public int solution(String s) {
        int answer = 0;
        char c = 0;
        int check1 = 0, check2 = 0;
        for (int i = 0; i < s.length(); i++) {
			if(c == 0) {
				c = s.charAt(i);
				check1 = 1;
				check2 = 0;
				continue;
			}
			
			if(c == s.charAt(i)) {
				check1++;
			}
			else {
				check2++;
			}
				
			
			if(check1 == check2) {
				c = 0;
				answer++;
			}
		}
        
        if(c != 0)
        	answer++;
        
        return answer;
    }
}