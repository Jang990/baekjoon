import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 1;
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line.contains("-")) {
                break;
            }

            int result = calcMinOperation(line);
            sb.append(idx + ". " + result + "\n");
            idx++;
        }
        br.close();

        System.out.println(sb);
    }

    private static int calcMinOperation(String line) {
        Stack<Character> st = new Stack<>();
        int wrongReverseCnt = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '{') {
                st.push(c);
            }
            else if (c == '}') {
                if (!st.isEmpty()) {
                    st.pop();
                }
                else {
                    wrongReverseCnt++;
                }
            }
        }

        int result = 0;

        if (st.size() % 2 == 0) {
            result += st.size() / 2;
        }
        else {
            result += (st.size()+1) / 2;
            wrongReverseCnt++;
        }

        result += wrongReverseCnt/2;
        
        return result;
    }
}
