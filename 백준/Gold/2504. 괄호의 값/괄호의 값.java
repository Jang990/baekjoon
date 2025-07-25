import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        br.close();

        List<String> correctKeys = List.of("(", ")", "[", "]");
        Stack<String> st = new Stack<>();
        int[] depth = new int[32];
        for (int i = 0; i < input.length; i++) {
            if (!correctKeys.contains(input[i])) {
                depth[1] = 0;
                break;
            }

            if (input[i].equals("[") || input[i].equals("(")) {
                st.push(input[i]);
                continue;
            }

            if (
                    st.isEmpty()
                            || (input[i].equals("]") && "(".equals(st.peek()))
                            || (input[i].equals(")") && "[".equals(st.peek()))
            ) {
                depth[1] = 0;
                break;
            }

            int num = input[i].equals("]") ? 3 : 2;
            if(depth[st.size() +1 ] == 0)
                depth[st.size()] += num;
            else
                depth[st.size()] += num * depth[st.size() + 1];
            depth[st.size() + 1] = 0;
            st.pop();
        }

        System.out.println(depth[1]);
    }
}
