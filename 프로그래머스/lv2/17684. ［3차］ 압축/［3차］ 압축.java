import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Solution {
    public int[] solution(String msg) {
        List<Integer> list = new ArrayList<>();
        
        Map<String, Integer> map = new HashMap<>();
        char c;
        for (int i = 0; i < 26; i++) {
			c = (char)(65+i);
			map.put(c + "", i+1);
		}
        
        int nowIdx = 0, val = 27;
        String word = "";
        while(nowIdx != msg.length()) {
        	word = "" + msg.charAt(nowIdx);
        	while(map.containsKey(word) && nowIdx < msg.length()-1) {
        		word += msg.charAt(nowIdx+1);
        		nowIdx++;
        	}
        	
        	if(map.containsKey(word)) {
        		list.add(map.get(word));
        		break;
        	}
        	else {
        		list.add(map.get(word.substring(0, word.length()-1)));
        		map.put(word, val++);
        	}
        	
        }
        
        
        
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}