import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();

        Stack<Integer> st = new Stack<>();
        int nextOrder = 1;
        for (int i : line) {
            if (i == nextOrder) {
                nextOrder++;
                continue;
            }
            while (!st.isEmpty() && st.peek() == nextOrder) {
                st.pop();
                nextOrder++;
            }
            st.push(i);
        }

        while (!st.isEmpty() && st.peek() == nextOrder) {
            st.pop();
            nextOrder++;
        }

        if(nextOrder > n) System.out.println("Nice");
        else System.out.println("Sad");
    }
}
