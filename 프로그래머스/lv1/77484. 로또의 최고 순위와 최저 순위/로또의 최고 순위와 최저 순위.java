import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        int unknown = 0;
        int correct = 0;
        
        List<Integer> list = Arrays.stream(win_nums)
        		.boxed().collect(Collectors.toList());
        
        for (int i = 0; i < lottos.length; i++) {
			if(lottos[i] == 0) {
				unknown++;
				continue;
			}
			if(list.contains(lottos[i]))
				correct++;
		}
        
        answer[0] = rank[correct + unknown];
        answer[1] = rank[correct];
        
        return answer;
    }
}