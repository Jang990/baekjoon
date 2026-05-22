import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0)
            return cities.length * 5;
        
        int result = 0;
        Queue<String> qu = new LinkedList<>();
        for (String cityOriginal : cities) {
            String city = cityOriginal.toLowerCase();
            if (qu.contains(city)) {
                qu.remove(city);
                result += 1;
            } else {
                if(qu.size() >= cacheSize)
                    qu.poll();
                result += 5;
            }
            qu.offer(city);
        }
        return result;
    }
}