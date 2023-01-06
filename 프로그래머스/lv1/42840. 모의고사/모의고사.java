import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Solution {
    public int[] solution(int[] answers) {
        int[] p1 = {1, 2, 3, 4, 5},
        	p2 = {2, 1, 2, 3, 2, 4, 2, 5},
        	p3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] cnt = new int[3];
        
        for (int i = 0; i < answers.length; i++) {
        	if(p1[i%p1.length] == answers[i])
        		cnt[0]++;
        	if(p2[i%p2.length] == answers[i])
        		cnt[1]++;
        	if(p3[i%p3.length] == answers[i])
        		cnt[2]++;
        }
        
        
        int max = Arrays.stream(cnt).max().getAsInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < cnt.length; i++) {
        	if(max== cnt[i])
        		list.add(i+1);
		}
        
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}