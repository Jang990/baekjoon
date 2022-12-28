import java.util.Arrays;
class Solution {
    public int[] solution(String s) {
        int[] answer = {0, 0};
        
        while(!s.equals("1")) {
        	answer[1] += Arrays.stream(s.split("")).filter((c) -> {
        		if(c.equals("0")) 
        			return true;
        		else 
        			return false;
        	}).count();
        	s = s.replace("0", "");
        	s = ""+ Integer.toBinaryString(s.length());
        	answer[0]++;
        }
        return answer;
    }
}