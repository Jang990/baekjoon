import java.util.HashMap;
class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> hash = new HashMap<>();
        for (String str : participant) {
			hash.put(str, hash.getOrDefault(str, 0)+1);
		}
        
        for (String str : completion) {
			int n = hash.get(str);
			if(n == 1)
				hash.remove(str);
			else
				hash.replace(str, n-1);
		}
        
        return hash.keySet().iterator().next();
    }
}