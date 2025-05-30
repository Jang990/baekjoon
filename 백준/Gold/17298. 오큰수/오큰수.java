import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();

        int[] result = new int[n];
        Stack<Element> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && st.peek().val < arr[i]) {
                Element element = st.pop();
                result[element.idx] = arr[i];
            }
            st.push(new Element(arr[i], i));
        }

        while (!st.isEmpty()) {
            Element element = st.pop();
            result[element.idx] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }

    static class Element {
        int val, idx;

        public Element(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
}
