import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        br.close();

        if (N == 1) {
            System.out.println(0);
            return;
        }

        Stack<Integer> st = new Stack<>();

        int[] result = new int[N];
        st.push(arr.length - 1);
        for (int i = arr.length-2; i >= 0; i--) {
            int beforeIdx = st.peek();
            int nowHeight = arr[i];
            while (arr[beforeIdx] <= nowHeight) {
                result[beforeIdx] = i+1;
                st.pop();

                if (st.isEmpty()) {
                    break;
                }

                beforeIdx = st.peek();
            }
            st.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(result[i] + " ");
        }

        System.out.println(sb.toString());
    }
}
