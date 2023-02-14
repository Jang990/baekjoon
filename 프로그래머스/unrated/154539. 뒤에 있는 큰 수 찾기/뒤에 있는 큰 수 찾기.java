import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);
        
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < numbers.length; i++) {
        	if(st.isEmpty() || numbers[st.peek()] >= numbers[i]) {
        		st.push(i);
        		continue;
        	}
        	
        	while(!st.isEmpty()) {
        		if(numbers[st.peek()] >= numbers[i])
        			break;
        		answer[st.pop()] = numbers[i];
        	}
        	st.push(i);
        }
        
        return answer;
    }
}