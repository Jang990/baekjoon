import java.util.stream.IntStream;
class Solution {
    public String solution(String s, int n) {
        n %= 26;
        int[] lowerCase = IntStream.range(97, 123).toArray();
        int[] upperCase = IntStream.range(65, 91).toArray();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if(97 <= c && c <= 122) 
        		c = (char)lowerCase[(c-97+n)%26];
        	else if(65 <= c && c <= 90)
        		c = (char)upperCase[(c-65+n)%26];
        	sb.append(c);
        }
        
        return sb.toString();
    }
}