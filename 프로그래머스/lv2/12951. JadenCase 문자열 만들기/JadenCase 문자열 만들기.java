class Solution {
    public String solution(String s) {
        String answer = "";
        
        char c1 = s.charAt(0), c2, resultC;
        
        if(isLowerCase(c1))
        	answer += (c1+"").toUpperCase();
        else
        	answer += c1;
        
        for (int i = 0; i < s.length()-1; i++) {
			c1 = s.charAt(i);
			c2 = s.charAt(i+1);
			resultC = c2;
			
			if(c1 == 32 && isLowerCase(c2)) {
				resultC = (char)(c2-32);
			}
			else if(c1 != 32 && isUpperCase(c2)) {
				resultC = (char)(c2+32);
			}
			
			answer += resultC;
		}
        
        return answer;
    }
	
	boolean isLowerCase(char c) {
		return 97 <= c && c <= 122;
	}
	
	boolean isUpperCase(char c) {
		return 65 <= c && c <= 90;
	}
}