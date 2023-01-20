import java.util.Arrays;  
import java.util.List;
class Solution {
    static List<String> oa = Arrays.asList("aya", "ye", "woo", "ma");
	public static int solution(String[] babbling) {
		int answer = 0;
		
		for (String string : babbling) {
			if(check(string))
				answer++;
		}
		
		return answer;
    }
	
	private static boolean check(String s) {
		int idx;
		char c = 0;
		while(s.length() != 0) {
			if(c == s.charAt(0))
				break;
			
			c = s.charAt(0);
			if(c == 'a' || c == 'w') {
				if(s.length() < 3)
					break;
				idx = 3;
			}
			else if(c == 'm' || c == 'y') {
				if(s.length() < 2)
					break;
				idx = 2;
			}
			else
				break;
			
			if(oa.contains(s.substring(0, idx)))
				s = s.substring(idx);
			else
				break;
		}
		
		if(s.length() == 0)
			return true;
		else 
			return false;
	}
}