import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String line = br.readLine();
            if (line.equals(".")) {
                break;
            }

            if (isBalance(line)) {
                sb.append("yes\n");
            }
            else {
                sb.append("no\n");
            }
        }
        br.close();
        System.out.println(sb);
    }

    private static boolean isBalance(String line) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '(' || c == '[') {
                st.push(c);
            } else if (c == ')') {
                if (!st.isEmpty() && st.peek() == '(') {
                    st.pop();
                }
                else {
                    return false;
                }
            } else if (c == ']') {
                if (!st.isEmpty() && st.peek() == '[') {
                    st.pop();
                }
                else {
                    return false;
                }
            }
        }

        if (!st.isEmpty()) {
            return false;
        }

        return true;
    }
}
