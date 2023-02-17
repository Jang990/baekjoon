import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String X, String Y) {
        StringBuilder sb = new StringBuilder();
        
        Map<Integer, Integer> xHash = new HashMap<>();
        Map<Integer, Integer> yHash = new HashMap<>();
        for (int i = 0; i < X.length(); i++) {
			int n = X.charAt(i) - '0';
			xHash.put(n, xHash.getOrDefault(n, 0)+1);
		}
        
        for (int i = 0; i < Y.length(); i++) {
			int n = Y.charAt(i) - '0';
			yHash.put(n, yHash.getOrDefault(n, 0)+1);
		}
        
        for (int i = 10; i >=0; i--) {
        	int min = Math.min(xHash.getOrDefault(i, 0), yHash.getOrDefault(i, 0));
        	for (int j = 0; j < min; j++) {
				sb.append(i);
			}
		}
        
        String answer = sb.toString();
        if(answer.length() == 0)
        	return "-1";
        if(answer.charAt(0) == '0')
        	return "0";
        
        return answer;
    }
}