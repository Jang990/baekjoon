import java.util.Stack;
class Solution {
    public String solution(String number, int k) {
        String answer = "";
        Stack<Integer> st = new Stack<>();
        st.push(number.charAt(0) - '0');
        for (int i = 1; i < number.length(); i++) {
            int n = number.charAt(i) - '0';
            while (!st.isEmpty() && st.peek() < n && k > 0) {
                st.pop();
                k--;
            }
            st.push(n);
        }

        while (k != 0) {
            st.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : st) {
            sb.append(num);
        }
        return sb.toString();
    }
}