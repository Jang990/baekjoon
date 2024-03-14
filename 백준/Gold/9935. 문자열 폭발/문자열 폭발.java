import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String base = br.readLine();
        String bomb = br.readLine();
        br.close();

        Stack<Stack<Character>> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < base.length(); i++) {
            char now = base.charAt(i);
            if (!st.isEmpty() && now == bomb.charAt(st.peek().size())) {
                st.peek().push(now);
                if (st.peek().size() == bomb.length())
                    st.pop();
                continue;
            }
            if (now == bomb.charAt(0)) {
                if(bomb.length() == 1)
                    continue;
                Stack<Character> start = new Stack<>();
                start.push(now);
                st.push(start);
                continue;
            }

            st.stream().map(stack -> stack.stream())
                    .forEach(stackStream -> stackStream.forEach(sb::append));
            st.clear();
            sb.append(now);
        }
        if (!st.isEmpty()) {
            st.stream().map(stack -> stack.stream())
                    .forEach(stackStream -> stackStream.forEach(sb::append));
        }

        if(sb.length() == 0)
            System.out.println("FRULA");
        else
            System.out.println(sb);
    }
}
