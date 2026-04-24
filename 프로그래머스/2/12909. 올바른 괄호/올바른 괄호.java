import java.util.*;
class Solution {
    static boolean solution(String s) {
        Stack<Character> st = new Stack<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == ')' && st.isEmpty())
                return false;

            if(arr[i] == '(')
                st.push('(');
            else
                st.pop();
        }

        return st.isEmpty();
    }
}