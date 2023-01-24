import java.util.*;
class Solution {
    boolean solution(String s) {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(')
                st.push('(');
            else {
                if(st.isEmpty()) {
                    st.push(')');
                    break;
                } 
                else
                    st.pop();
            }
        }
        
        return st.isEmpty();
    }
}