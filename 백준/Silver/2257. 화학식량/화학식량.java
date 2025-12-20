import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        br.close();

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '(') {
                st.push(-1);
                continue;
            }
            if (c == ')') {
                int sum = 0;
                while (!st.isEmpty()) {
                    int pop = st.pop();
                    if(pop == -1)
                        break;
                    sum += pop;
                }
                st.push(sum);
                continue;
            }
            if (isNumber(c)) {
                int pop = st.pop();
                st.push(pop * (c - '0'));
                continue;
            }
            int weight = toNumber(c);
            st.push(weight);
        }

        System.out.println(st.stream().mapToInt(Integer::valueOf).sum());
    }

    private static boolean isNumber(char c) {
        int number = c - '0';
        return 2 <= number && number <= 9;
    }

    private static int toNumber(char c) {
        if(c == 'H')
            return 1;
        if(c == 'C')
            return 12;
        if(c == 'O')
            return 16;
        throw new IllegalArgumentException();
    }
}
