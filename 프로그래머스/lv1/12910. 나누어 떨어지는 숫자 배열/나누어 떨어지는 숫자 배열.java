import java.util.ArrayList;
import java.util.List;
class Solution {
    public int[] solution(int[] arr, int divisor) {
        List<Integer> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] % divisor == 0)
				list.add(arr[i]);
		}
		
		int[] answer = list.stream().sorted().mapToInt(Integer::valueOf).toArray(); 
        return (answer.length == 0) ? new int[] {-1} : answer;
    }
}