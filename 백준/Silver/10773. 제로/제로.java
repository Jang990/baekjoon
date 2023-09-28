import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int line = Integer.valueOf(br.readLine());

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < line; i++) {
            int num = Integer.valueOf(br.readLine());
            if (num == 0) {
                st.pop();
                continue;
            }
            st.push(num);
        }
        br.close();

        int result = 0;
        while (!st.isEmpty()) {
            result += st.pop();
        }

        System.out.println(result);
    }
}
