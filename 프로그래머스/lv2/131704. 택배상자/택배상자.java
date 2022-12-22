import java.util.Stack;
class Solution {
    public int solution(int[] order) {
        Stack<Integer> st = new Stack<>();
        int orderIdx = 0;
        
        for (int boxIdx = 1; boxIdx <= order.length; boxIdx++) {
			if(order[orderIdx] == boxIdx) {
				orderIdx++;
				continue;
			}
			
			if(st.empty()) {
				st.push(boxIdx);
				continue;
			}
			
			
			if(st.lastElement() == order[orderIdx]) {
				st.pop();
				orderIdx++;
				boxIdx--;
			}
			else {
				st.push(boxIdx);
			}
		}
        
        while(!st.empty() && st.lastElement() == order[orderIdx]) {
			st.pop();
			orderIdx++;
		}
        
        
        return orderIdx;
    }
}