import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    private static String[] arr;
    private static boolean[] visited = new boolean[10];
    static long max = Long.MIN_VALUE;
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        arr = br.readLine().split(" ");
        br.close();
        for (int i = 0; i < 10; i++) {
            visited[i] = true;
            Stack<Integer> stack = new Stack<>();
            stack.push(i);
            Rec(0, stack);
            stack.pop();
            visited[i] = false;
        }


        double n = Math.pow(10, arr.length);
        String maxStr = String.valueOf(max);
        String minStr = String.valueOf(min);
        if (n > max) {
            maxStr = "0" + maxStr;
        }
        if (n > min) {
            minStr = "0" + minStr;
        }
        System.out.println(maxStr);
        System.out.println(minStr);
    }

    private static void Rec(int depth, Stack<Integer> stack) {
        if (depth == arr.length) {
            StringBuilder sb = new StringBuilder();
            for (int num : stack) {
                sb.append(num);
            }
            max = Math.max(Long.valueOf(max), Long.valueOf(sb.toString()));
            min = Math.min(Long.valueOf(min), Long.valueOf(sb.toString()));
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (visited[i])
                continue;

            if (arr[depth].equals("<") && stack.peek() >= i) {
                continue;
            }
            else if (arr[depth].equals(">") && stack.peek() <= i) {
                continue;
            }

            visited[i] = true;
            stack.push(i);
            Rec(depth+1, stack);
            stack.pop();
            visited[i] = false;
        }
    }
}
