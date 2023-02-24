import java.util.ArrayList; 
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
class Solution {
    static Map<String, Integer> map;
	public static String[] solution(String[] orders, int[] course) {
        List<String> list = new ArrayList<>();
        for (int i = course.length-1; i >= 0; i--) {
        	map = new HashMap<>();
        	for (int j = 0; j < orders.length; j++) {
        		String[] strs = orders[j].split("");
        		Arrays.sort(strs);
        		dfs(course[i], -1, "", strs);
			}
        	
        	if(map.size() < 1)
        		continue;
        	
        	int max = map.values().stream().mapToInt(Integer::valueOf).max().getAsInt();
            if(max == 1)
        		continue;
            
        	for (Entry<String, Integer> entry : map.entrySet()) {
				if(entry.getValue() != max)
					continue;
				
				list.add(entry.getKey());
			}
        	
		}
        
        return list.stream().sorted().toArray(String[]::new);
    }
	
	public static void dfs(int max, int idx, String str, String[] strings) {
		if(max == str.length()) {
			map.put(str, map.getOrDefault(str, 0)+1);
			return;
		}

		for (int i = idx+1; i < strings.length; i++) {
			dfs(max, i, str+strings[i], strings);
		}
	}
}