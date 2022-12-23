import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < 10; i++) {
			map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
		}
        
        if(check(want, number, map))
        	answer++;
        
        for (int i = 10; i < discount.length; i++) {
        	int minusCnt = map.getOrDefault(discount[i-10], 0);
        	if(minusCnt <= 1)
        		map.remove(discount[i-10]);
        	else
        		map.put(discount[i-10], minusCnt-1);
        	
        	map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
        	
        	if(check(want, number, map))
            	answer++;
		}
        
        return answer;
    }
    
    private boolean check(String[] want, int[] number, HashMap<String, Integer> map) {
		for (int i = 0; i < number.length; i++) {
			if(map.getOrDefault(want[i], 0) != number[i])
				return false;
		}
		return true;
	}
}