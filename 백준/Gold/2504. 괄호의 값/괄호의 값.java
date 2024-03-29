import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        br.close();
        System.out.println(calc(line));
    }

    private static int calc(String line) {
        Stack<Character> st = new Stack<>();
        int mul = 1;
        int result = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            switch (c) {
                case '(':
                    mul *= 2;
                    st.push(c);
                    break;
                case '[':
                    mul *= 3;
                    st.push(c);
                    break;
                case ')':
                    if(st.isEmpty() || st.peek() != '(')
                        return 0;
                    if(line.charAt(i-1) == '(') result += mul;
                    mul /= 2;
                    st.pop();
                    break;
                case ']':
                    if(st.isEmpty() || st.peek() != '[')
                        return 0;
                    st.pop();
                    if(line.charAt(i-1) == '[') result += mul;
                    mul /= 3;
                    break;
            }
        }

        return st.isEmpty() ? result : 0;
    }
}
