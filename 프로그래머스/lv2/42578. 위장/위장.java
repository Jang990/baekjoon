import java.util.HashMap;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<>();
        for (String[] strings : clothes) {
        	map.put(strings[1], map.getOrDefault(strings[1], 0)+1);
        }
        
        for (int n : map.values()) {
			answer *= n+1;
		}
        
        return answer-1;
    }
}