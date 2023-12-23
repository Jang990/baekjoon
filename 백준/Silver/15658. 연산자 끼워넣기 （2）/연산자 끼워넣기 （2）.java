import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[] arr;
    private static int[] operator;
    private static final int PLUS_IDX = 0;
    private static final int MINUS_IDX = 1;
    private static final int MUL_IDX = 2;
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        operator = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::valueOf).toArray();
        br.close();

        int[] ops = new int[arr.length - 1];
        rec(0, ops);

        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n").append(min).append("\n");
        System.out.println(sb);
    }

    private static void rec(int depth, int[] ops) {
        if (depth == arr.length-1) {
            int result = compute(ops);
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        for (int idx = 0; idx < 4; idx++) {
            if(operator[idx] == 0)
                continue;

            ops[depth] = idx;
            operator[idx]--;
            rec(depth + 1, ops);
            operator[idx]++;
        }
    }

    private static int compute(int[] ops) {
        int n1 = arr[0];
        int n2 = 0;
        for (int i = 0; i < ops.length; i++) {
            n2 = arr[i+1];
            int result = 0;
            switch (ops[i]) {
                case PLUS_IDX:
                    result = n1 + n2;
                    break;
                case MINUS_IDX:
                    result = n1 - n2;
                    break;
                case MUL_IDX:
                    result = n1 * n2;
                    break;
                default:
                    result = n1 / n2;
            }
            n1 = result;
        }

        return n1;
    }
}
