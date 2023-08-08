import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static final Stack<Integer> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String opt = st.nextToken();
            String result = "";
            if (opt.equals("push")) {
                stack.push(Integer.valueOf(st.nextToken()));
            } else if (opt.equals("pop")) {
                result = String.valueOf(pop());
            } else if (opt.equals("size")) {
                result = String.valueOf(stack.size());
            } else if (opt.equals("empty")) {
                result = String.valueOf(empty());
            } else if (opt.equals("top")) {
                result = String.valueOf(top());
            }

            sb.append(result);
            if (result.length() >= 1) {
                sb.append("\n");
            }
        }

        br.close();
        System.out.println(sb);
    }

    private static int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    private static int empty() {
        if (stack.isEmpty()) {
            return 1;
        }
        return 0;
    }

    private static int pop() {
        if (stack.size() == 0) {
            return -1;
        }
        return stack.pop();
    }
}
