import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Comparator;
class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int max = -1;
        
        for (int i = 0; i < number.length() - k; i++) {
        	max = -1;
        	for (int j = start; j <= k+i; j++) {
        		int n = number.charAt(j) - '0';
				if(max < n) {
					max = n;
					start = j+1;
				}
			}
        	sb.append(max);
		}
		
        return sb.toString();
    }
}