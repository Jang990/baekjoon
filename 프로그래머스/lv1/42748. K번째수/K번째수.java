import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
class Solution {
    public static int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
        for (int i = 0; i < answer.length; i++) {
			int start = commands[i][0], 
				end = commands[i][1],
				num = commands[i][2];
			
			List<Integer> list = new ArrayList<>();
			for (int j = start-1; j < end; j++) {
				list.add(array[j]);
			}
			list.sort(Comparator.naturalOrder());
			answer[i] = list.get(num-1);
		}
        
        return answer;
    }
}