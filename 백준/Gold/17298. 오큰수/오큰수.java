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
                .mapToInt(Integer::valueOf).toArray();
        int[] result = new int[n];
        br.close();

        Stack<Integer> idxStack = new Stack<>();
        for(int i = 0; i < n; i++) {
            while (!idxStack.isEmpty()) {
                int nowNum = arr[i];
                if (arr[idxStack.peek()] < nowNum) {
                    int idx = idxStack.pop();
                    result[idx] = nowNum;
                } else {
                    break;
                }
            }
            idxStack.push(i);
        }

        while (!idxStack.isEmpty()) {
            int idx = idxStack.pop();
            result[idx] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
