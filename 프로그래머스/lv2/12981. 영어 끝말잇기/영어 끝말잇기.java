import java.util.HashSet;
import java.util.Set;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        boolean flag = false;
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        
        int turn = 0;
        for (int i = 0; i < words.length-1; i++) {
        	if(words[i].charAt(words[i].length()-1) != words[i+1].charAt(0) || set.contains(words[i+1])) {
        		flag = true;
        		turn = i;
        		break;
        	}
        	set.add(words[i+1]);
		}
        
        if(flag) {
        	turn++;
        	answer[0] = turn % n + 1;
        	answer[1] = turn / n + 1;
        }
        
        return answer;
    }
}