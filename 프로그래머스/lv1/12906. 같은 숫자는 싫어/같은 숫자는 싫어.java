import java.util.*;

public class Solution {
    public static int[] solution(int[] arr) {
		Stack<Integer> st = new Stack<>();
		st.push(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if(st.peek() == null || st.peek() != arr[i])
				st.push(arr[i]);
        }
        
        return st.stream().mapToInt(Integer::valueOf).toArray();
    }
}