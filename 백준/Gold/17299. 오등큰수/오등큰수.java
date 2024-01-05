import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] base = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : base) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Stack<Integer> st = new Stack<>();
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int now = base[i];

            while (!st.isEmpty()) {
                int peek = st.peek();
                if (map.get(now) >= map.get(peek)) {
                    st.pop();
                    continue;
                }
                result[i] = peek;
                st.push(now);
                break;
            }

            if (st.isEmpty()) {
                st.push(now);
                result[i] = -1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}
