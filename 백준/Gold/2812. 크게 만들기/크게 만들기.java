import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.valueOf(st.nextToken());
        int removeCnt = Integer.valueOf(st.nextToken());
        String numbers = br.readLine();
        Stack<Integer> stack = new Stack<>();
        stack.push(numbers.charAt(0) - '0');

        for (int i = 1; i < numbers.length(); i++) {
            int now = numbers.charAt(i) - '0';
            while (!stack.isEmpty() && stack.peek() < now && removeCnt != 0) {
                stack.pop();
                removeCnt--;
            }
            stack.push(now);
        }

        while (!stack.isEmpty() && removeCnt != 0) {
            stack.pop();
            removeCnt--;
        }

        StringBuilder sb = new StringBuilder();
        stack.forEach(sb::append);
        System.out.println(sb);

        br.close();
    }
}
