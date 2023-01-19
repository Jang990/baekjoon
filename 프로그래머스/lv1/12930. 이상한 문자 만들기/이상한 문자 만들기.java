class Solution {
    public String solution(String s) {
        s = s.toLowerCase();
		char c= s.charAt(0);
		if(97 <= c && c <= 122)
			c = (char)(c-32);
		
        String answer = "" + c;
        int startIdx = 0;
        
        for (int i = 1; i < s.length(); i++) {
			c= s.charAt(i);
			if(s.charAt(i-1) == 32)
				startIdx = i;
			
			if((i-startIdx)%2 == 0 && 97 <= c && c <= 122)
				c = (char)(c-32);
			
			answer += c;
		}
        
        return answer;
    }
}