import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < tangerine.length; i++) {
			int num = map.getOrDefault(tangerine[i], 0); 
			map.put(tangerine[i], ++num);
		}
		
		List<Map.Entry<Integer, Integer>> list = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
		
		
		for (int i = list.size()-1; i >= 0; i--) {
			if(k <= 0)
				break;
			answer++;
			k -= list.get(i).getValue();
		}
        
        return answer;
    }
}