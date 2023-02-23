import java.util.*;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        Map<Character, Integer> map = new HashMap<>();
        
        for(int i = 0; i < keymap.length; i++) {
            for(int j = 0; j < keymap[i].length(); j++) {
                char c = keymap[i].charAt(j);
                if(map.containsKey(c)) {
                    map.put(c, Math.min(map.get(c), j+1));
                }
                else
                    map.put(c, j+1);
            }
        }
        
        for(int i = 0; i < targets.length; i++) {
            int sum = 0;
            for(int j = 0; j  < targets[i].length(); j++) {
                char c = targets[i].charAt(j);
                if(map.containsKey(c)) {
                  sum += map.get(c);
                }
                else {
                    sum = -1;

                  break;
                    
                }
            }
            answer[i] = sum;
        }
        
        return answer;
    }
}