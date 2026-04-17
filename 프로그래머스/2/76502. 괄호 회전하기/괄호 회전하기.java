import java.util.LinkedList;
import java.util.Stack;

class Solution {
    public static int solution(String s) {
        LinkedList<Character> list = new LinkedList<>();
        for (char c : s.toCharArray())
            list.add(c);

        int result = isRight(list) ? 1 : 0;
        for (int i = 0; i < s.length() - 1; i++) {
            list.addLast(list.removeFirst());
            if (isRight(list))
                result++;
        }
        return result;
    }

    private static boolean isRight(LinkedList<Character> list) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            char c = list.get(i);
            if (st.isEmpty() || c == '(' || c == '[' || c == '{') {
                st.push(c);
                continue;
            }

            if(st.peek() == '(' && c == ')')
                st.pop();
            else if(st.peek() == '[' && c == ']')
                st.pop();
            else if(st.peek() == '{' && c == '}')
                st.pop();
        }

        return st.isEmpty();
    }
}