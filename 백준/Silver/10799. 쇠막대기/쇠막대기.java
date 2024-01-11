import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        br.close();

        Stack<Character> st = new Stack<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                result++;
                st.pop();
                continue;
            }

            if (isLazer(s.charAt(i + 1))) {
                result += st.size();
                i++;
            } else {
                st.push(c);
            }
        }

        System.out.println(result);
    }

    private static boolean isLazer(char c) {
        return c == ')';
    }
}
