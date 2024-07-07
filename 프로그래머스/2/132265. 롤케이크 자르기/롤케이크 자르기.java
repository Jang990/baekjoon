import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int result = 0;
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        for (int n : topping) {
            map1.put(n, map1.getOrDefault(n, 0) + 1);
        }

        for (int n : topping) {
            int now = map1.get(n) - 1;
            if(now < 1)
                map1.remove(n);
            else
                map1.put(n, now);

            map2.put(n, map2.getOrDefault(n, 0) + 1);
            if(map1.size() == map2.size())
                result++;
        }

        return result;
    }
}