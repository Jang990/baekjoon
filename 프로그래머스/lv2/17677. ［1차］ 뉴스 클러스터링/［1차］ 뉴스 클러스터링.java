import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
class Solution {
    Set<String> unionSet;
	
	public int solution(String str1, String str2) {
        unionSet = new HashSet<>();
        int intersection = 0, union = 0;
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        Map<String, Integer> s1 = initHash(str1);
      	Map<String, Integer> s2 = initHash(str2);
        
        if(s1.size() == 0 && s2.size() == 0)
      		return 65536;
      	
      	for (String string : unionSet) {
      		System.out.print(string + " ");
      		int num1 = s1.getOrDefault(string, s2.get(string));
      		int num2 = s2.getOrDefault(string, s1.get(string));
      		
      		union += Math.max(num1, num2);
      		if(s1.containsKey(string) && s2.containsKey(string)) {
      			intersection += Math.min(num1, num2);
      		}
		}
      	
      	System.out.println(intersection + "," + union);
      	
        return (int)Math.floor((double)intersection/union * 65536);
    }

	private Map<String, Integer> initHash(String str1) {
		Map<String, Integer> map = new HashMap<>();
		
      	for (int i = 0; i < str1.length() - 1; i++) {
      		char c1 = str1.charAt(i);
      		char c2 = str1.charAt(i+1);
      		if(c1 > 122 || c1 < 97)
      			continue;
      		if(c2 > 122 || c2 < 97)
      			continue;
      		String s = c1+""+c2;
      		unionSet.add(s);
      		map.put(s, map.getOrDefault(s, 0)+1);
		}
      	
		return map;
	}
}