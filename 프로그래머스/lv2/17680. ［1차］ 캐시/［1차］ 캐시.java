import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0)
            return cities.length * 5;
        
        int answer = 0;
        Queue<String> cache = new LinkedList<>();
        for (String str : cities) {
            str = str.toLowerCase();
			if(cache.contains(str)) {
				cache.remove(str);
				answer += 1;
			}
			else {
				if(cache.size() == cacheSize) {
					cache.poll();
				}
				answer += 5;
			}
            cache.offer(str);
		}
        
        return answer;
    }
}