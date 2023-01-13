import java.util.Arrays;
class Solution {
    public int[] solution(int[] arr) {
        int min = Arrays.stream(arr).min().getAsInt();
        int[] answer = Arrays.stream(arr).filter(n -> min != n).toArray();
        if(answer.length == 0)
        	return new int[] {-1};
        
        return answer;
    }
}