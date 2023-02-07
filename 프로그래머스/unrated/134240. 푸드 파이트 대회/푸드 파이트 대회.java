import java.util.Stack;
class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> st = new Stack<>();
        for (int i = 1; i < food.length; i++) {
        	int now = food[i]/2;
        	int cnt = 0;
        	while(cnt < now) {
        		sb.append(i);
        		st.add(i);
        		cnt++;
        	}
		}
        
        sb.append(0);
        
        while(!st.isEmpty()) {
        	sb.append(st.pop());
        }
        
        return sb.toString();
    }
}