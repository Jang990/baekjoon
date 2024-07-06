import java.util.Stack;
class Solution {
    public int solution(int[] order) {
        int orderIdx = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 1; i <= order.length; i++) {
            while (!st.isEmpty() && st.peek() == order[orderIdx]) {
                st.pop();
                orderIdx++;
            }

            if(i == order[orderIdx]) orderIdx++;
            else st.push(i);
        }
        while (!st.isEmpty() && st.peek() == order[orderIdx]) {
            st.pop();
            orderIdx++;
        }

        return orderIdx;
    }
}