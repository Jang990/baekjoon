class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if(97 <= c && c <= 122) 
        		c = (char)((c-97+n)%26+'a');
        	else if(65 <= c && c <= 90)
        		c = (char)((c-65+n)%26+'A');
        	sb.append(c);
        }
        
        return sb.toString();
    }
}